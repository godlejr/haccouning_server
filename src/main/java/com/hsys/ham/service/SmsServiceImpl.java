package com.hsys.ham.service;

import java.util.Calendar;
import java.util.List;

import javax.el.ExpressionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hsys.ham.common.utils.LuisWebServiceUtil;
import com.hsys.ham.common.utils.sms.HamSms;
import com.hsys.ham.common.utils.sms.HamSmsEntity;
import com.hsys.ham.common.utils.sms.HamSmsIntent;
import com.hsys.ham.dao.CardCompDao;
import com.hsys.ham.dao.CardHistoryDao;
import com.hsys.ham.dao.RegisteredCardDao;
import com.hsys.ham.dto.SmsTransferDto;
import com.hsys.ham.repository.CardCompRepository;
import com.hsys.ham.repository.CardHistoryRepository;
import com.hsys.ham.repository.OraFunctionCallRepository;

/**
 * Sms 처리 관련 서비스 클래스
 * 
 * 
 * @author 김동주 사원
 * 
 * @since 2019.03.27
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SmsServiceImpl implements SmsService {
	@Autowired
	private LuisWebServiceUtil luisWebServiceUtil;

	@Autowired
	private CardHistoryRepository cardHistoryRepository;

	@Autowired
	private CardCompRepository cardCompRepository;

	@Autowired
	private OraFunctionCallRepository oraFunctionCallRepository;

	@Override
	public CardHistoryDao preprocessAndSaveAndGetCardHistoryDao(SmsTransferDto smsTransferDto) throws Exception {

		System.out.println(smsTransferDto.toString());
		
		String compPhone = smsTransferDto.getCompPhone();
	
		// 카드 문자 인 경우
		CardCompDao cardCompDao = this.cardCompRepository.getCardCompDaoByCompPhone(compPhone);

		if (cardCompDao != null) {

			String empNo = smsTransferDto.getEmpNo();
			String compCd = smsTransferDto.getCompCd();
			String cardCompCd = cardCompDao.getCompCd();

			List<RegisteredCardDao> registeredCardDaos = oraFunctionCallRepository
					.getRegisteredCardDaoByCompCdAndEmpNoAndCardNoAndCardCompCd(compCd, empNo, "", cardCompCd);

			int registeredCardDaoSize = registeredCardDaos.size();

			// 등록된 법인카드가 있는지
			if (registeredCardDaoSize > 0) {
				String smsMessage = smsTransferDto.getMessage();
				// 루이스 호출
				HamSms hamSms = this.luisWebServiceUtil.getHamSmsByMachineLearing(smsMessage);

				CardHistoryDao cardHistoryDao = new CardHistoryDao();

				HamSmsIntent topScoringIntent = hamSms.getTopScoringIntent();
				String intent = topScoringIntent.getIntent();

				// 카드 승인여부
				if ("CARD_TRAN_APPROVE".equals(intent)) {
					cardHistoryDao.setCancPartYn("A");
				}

				if ("CARD_TRAN_CANCEL".equals(intent)) {
					cardHistoryDao.setCancPartYn("B");
				}

				// 엔티티 분기처리
				HamSmsEntity[] entities = hamSms.getEntities();

				// 필수 데이터 유무 체크(금액, 승일일자, 승인시간, 사용처)
				boolean isAmtTotNull = false;
				boolean isTransMonthNull = false;
				boolean isTransDayNull = false;
				boolean isTransHourNull = false;
				boolean isTransMinuteNull = false;
				boolean isUsageNull = false;

				String transMonth = "";
				String transDay = "";
				String transHour = "";
				String transMinute = "";
				String transSecond = "00";

				for (HamSmsEntity entity : entities) {
					String type = entity.getType();
					String value = entity.getEntity();

					if ("AMT_TOT".equals(type)) {
						String filteredValue = value.replace(",", " ");
						cardHistoryDao.setAmtTot(Integer.parseInt(filteredValue.replace(" ", "")));
						isAmtTotNull = true;
					}

					if ("CARD_NO".equals(type)) {
						String filteredValue = value.replace("*", "_");
						cardHistoryDao.setCardNo(filteredValue.replace(" ", ""));
					}

					if ("USAGE".equals(type)) {
						cardHistoryDao.setVendorNm(value.replace(" ", ""));
						isUsageNull = true;
					}

					if ("TRAN_MONTH".equals(type)) {
						transMonth = value.replace(" ", "");
						isTransMonthNull = true;
					}

					if ("TRAN_DAY".equals(type)) {
						transDay = value.replace(" ", "");
						isTransDayNull = true;
					}

					if ("TRAN_HOUR".equals(type)) {
						transHour = value.replace(" ", "");
						isTransHourNull = true;
					}

					if ("TRAN_MINUTE".equals(type)) {
						transMinute = value.replace(" ", "");
						isTransMinuteNull = true;
					}

				}

				if (isAmtTotNull && isTransMonthNull && isTransDayNull && isTransHourNull && isTransMinuteNull
						&& isUsageNull) {
					int year = Calendar.getInstance().get(Calendar.YEAR);
					String transDate = year + transMonth + transDay;
					String transTime = transHour + transMinute + transSecond;

					cardHistoryDao.setTransDate(transDate);
					cardHistoryDao.setTransTime(transTime);

					// 통화코드 설정
					// 국내만 일단 적용
					cardHistoryDao.setCurrCd("KRW");
					cardHistoryDao.setAbroadYn("N");

					cardHistoryDao.setCompCd(compCd);
					cardHistoryDao.setEmpNo(empNo);

					cardHistoryDao.setCardCompDao(cardCompDao);
					cardHistoryDao.setStatus("1"); // 1: 미작성
					cardHistoryDao.setMessage(smsMessage);
					
					List<RegisteredCardDao> realRegisteredCardDaos = oraFunctionCallRepository
							.getRegisteredCardDaoByCompCdAndEmpNoAndCardNoAndCardCompCd(compCd, empNo, cardHistoryDao.getCardNo(),cardCompCd);

					int realRegisteredCardDaosSize = realRegisteredCardDaos.size();

					if (realRegisteredCardDaosSize == 1) {
						RegisteredCardDao realRegisteredCardDao = realRegisteredCardDaos.get(0);
						String realCardNo = realRegisteredCardDao.getCardNo();
						
						cardHistoryDao.setCardNo(realCardNo);
							
						cardHistoryRepository.saveCardHistoryDaoAndGetSeqno(cardHistoryDao);

						CardHistoryDao insertedCardHistoryDao = cardHistoryRepository
								.getCardHistoryDaoBySeqNo(cardHistoryDao.getSeqNo());

						return insertedCardHistoryDao;
					} else {
						
						//
						return null;
					}

				} else {
					// 필수 데이터 누락될 때

					// insert log..

				}

			} else {
				// 법카 확인 안될때
				return null;
			}
			// 등록된 카드가 아닐때
			return null;

		} else {
			// 등록된 카드사가 아닐때
			return null;
		}

	}

}
