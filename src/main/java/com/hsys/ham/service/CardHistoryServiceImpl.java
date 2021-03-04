package com.hsys.ham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hsys.ham.dao.CardHistoryDao;
import com.hsys.ham.repository.CardHistoryRepository;
import com.hsys.ham.repository.OraFunctionCallRepository;
import com.hsys.ham.repository.SlipDetailRepository;
import com.hsys.ham.repository.SlipHeaderRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CardHistoryServiceImpl implements CardHistoryService {

	@Autowired
	private CardHistoryRepository cardHistoryRepository;

	@Autowired
	private SlipHeaderRepository slipHeaderRepository;

	@Autowired
	private SlipDetailRepository slipDetailRepository;
	
	@Autowired
	private OraFunctionCallRepository oraFunctionCallRepository;

	@Override
	public List<CardHistoryDao> getCardHistoryDaosByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(
			String status, String compCd, String empNo, long offset, long limit) throws Exception {

		List<CardHistoryDao> CardHistoryDaos = cardHistoryRepository
				.getCardHistoryDaosByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(status,
						compCd, empNo, offset, limit);

		return CardHistoryDaos;
	}

	@Override
	public CardHistoryDao getCardHistoryDaoByModifyingCardHistoryDao(CardHistoryDao cardHistoryDao) throws Exception {

		cardHistoryRepository.modifyCardHistoryDao(cardHistoryDao);

		long seqNo = cardHistoryDao.getSeqNo();

		return cardHistoryRepository.getCardHistoryDaoBySeqNo(seqNo);
	}

	@Override
	public Boolean getIsSuccessByDeletingCardHistoryDaosAndSlipHeaderDaos(List<CardHistoryDao> cardHistoryDaos)
			throws Exception {

		Boolean isSuccess = false;

		try {
			for (CardHistoryDao cardHistoryDao : cardHistoryDaos) {
				long seqNo = cardHistoryDao.getSeqNo();
				String mngNoMobile = cardHistoryDao.getMngNoMobile();

				if (mngNoMobile != null) {
					slipHeaderRepository.deleteSlipHeaderDaoByMngNoMobile(mngNoMobile);
					slipDetailRepository.deleteSlipDetailDaoByMngNoMobile(mngNoMobile);
				}

				cardHistoryRepository.deleteCardHistoryDaoBySeqNo(seqNo);
			}

			isSuccess = true;

		} catch (Exception e) {
			isSuccess = false;
		}

		return isSuccess;
	}

	@Override
	public Boolean getIsSuccessByDeletingSlipHeaderDaosAndModifyingCardHistoryDaos(List<CardHistoryDao> cardHistoryDaos)
			throws Exception {
		Boolean isSuccess = false;

		try {
			for (CardHistoryDao cardHistoryDao : cardHistoryDaos) {

				String mngNoMobile = cardHistoryDao.getMngNoMobile();
				long seqNo = cardHistoryDao.getSeqNo();

				slipHeaderRepository.deleteSlipHeaderDaoByMngNoMobile(mngNoMobile);
				slipDetailRepository.deleteSlipDetailDaoByMngNoMobile(mngNoMobile);

				CardHistoryDao modifiedCardHistoryDao = new CardHistoryDao();
				modifiedCardHistoryDao.setStatus("1"); // 미작성 상태로 되돌리기
				modifiedCardHistoryDao.setMngNoMobile(null);// null 초기화
				modifiedCardHistoryDao.setSeqNo(seqNo);

				cardHistoryRepository.modifyCardHistoryDao(modifiedCardHistoryDao);
				
				oraFunctionCallRepository.modifySlipErpInterface(mngNoMobile);

			}

			isSuccess = true;

		} catch (Exception e) {
			isSuccess = false;

		}

		return isSuccess;
	}

	@Override
	public Integer getCardHistoryDaoCountByStatusAndEmpNo(String status,String  empNo) throws Exception {
		return cardHistoryRepository.getCardHistoryDaoCountByStatusAndEmpNo(status,empNo);
	}

}
