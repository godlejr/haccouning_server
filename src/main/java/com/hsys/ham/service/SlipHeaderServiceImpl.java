package com.hsys.ham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hsys.ham.dao.BudgetCheckDao;
import com.hsys.ham.dao.CardHistoryDao;
import com.hsys.ham.dao.SlipDetailDao;
import com.hsys.ham.dao.SlipHeaderDao;
import com.hsys.ham.repository.CardHistoryRepository;
import com.hsys.ham.repository.OraFunctionCallRepository;
import com.hsys.ham.repository.SlipDetailRepository;
import com.hsys.ham.repository.SlipHeaderRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SlipHeaderServiceImpl implements SlipHeaderService {

	@Autowired
	private SlipHeaderRepository slipHeaderRepository;

	@Autowired
	private SlipDetailRepository slipDetailRepository;

	@Autowired
	private OraFunctionCallRepository oraFunctionCallRepository;

	@Autowired
	private CardHistoryRepository cardHistoryRepository;

	@Override
	public SlipHeaderDao getSlipHeaderDaoBySavingSlipHeaderAndSlipDetail(SlipHeaderDao slipHeaderDao) throws Exception {

		// header 저장
		slipHeaderRepository.saveSlipHeaderDaoAndGetMngNoMobile(slipHeaderDao);
		String mngNoMobile = slipHeaderDao.getMngNoMobile();

		// detail 세팅 및 저장
		List<SlipDetailDao> slipDetails = slipHeaderDao.getSlipDetails();
		int slipDetailsSize = slipDetails.size();
		for (int i = 0; i < slipDetailsSize; i++) {
			slipDetails.get(i).setMngNoMobile(mngNoMobile);
		}

		slipDetailRepository.saveSlipDetailDaos(slipDetails);

		SlipHeaderDao newSlipHeaderDao = slipHeaderRepository.getSlipHeaderDaoByMngNoMobile(mngNoMobile);

		System.out.println(newSlipHeaderDao.getSlipDetails().size());
		return newSlipHeaderDao;

	}

	@Override
	public SlipHeaderDao getSlipHeaderDaoByMngNoMobile(String mngNoMobile) throws Exception {
		SlipHeaderDao slipHeaderDao = slipHeaderRepository.getSlipHeaderDaoByMngNoMobile(mngNoMobile);

		System.out.println(slipHeaderDao.getSlipDetails().size());
		return slipHeaderDao;
	}

	@Override
	public SlipHeaderDao getSlipHeaderDaoByModifyingSlipHeaderAndSlipDetail(SlipHeaderDao slipHeaderDao)
			throws Exception {

		slipHeaderRepository.modifySlipHeaderDao(slipHeaderDao);

		List<SlipDetailDao> slipDetails = slipHeaderDao.getSlipDetails();
		slipDetailRepository.modifySlipDetailDaos(slipDetails);

		String mngNoMobile = slipHeaderDao.getMngNoMobile();
		SlipHeaderDao modifiedSlipHeaderDao = slipHeaderRepository.getSlipHeaderDaoByMngNoMobile(mngNoMobile);
		System.out.println(modifiedSlipHeaderDao.getSlipDetails().size());
		return modifiedSlipHeaderDao;
	}

	@Override
	public BudgetCheckDao getBudgetCheckDaoByTransferringSlipHeaders(List<SlipHeaderDao> slipHeaderDaos)
			throws Exception {
		BudgetCheckDao budgetCheckDao = null;
		for (SlipHeaderDao slipHeaderDao : slipHeaderDaos) {

			String compCd = slipHeaderDao.getCompCd();
			String posDate = slipHeaderDao.getPosDate();
			String tripCd = slipHeaderDao.getBusiTripId();

			SlipDetailDao slipDetailDao = slipHeaderDao.getSlipDetails().get(0);
			String acntCd = slipDetailDao.getAcntCd();
			String deptCd = slipDetailDao.getDeptCd();
			String amtTot = String.valueOf((int) (float) slipDetailDao.getAmtTot());

			budgetCheckDao = oraFunctionCallRepository
					.getBudgetCheckDaoByCompCdAndPosDateAndDeptCdAndAcntCdAndAmtTotOrTripCd(compCd, posDate, deptCd,
							acntCd, amtTot, tripCd);

			String returnCd = budgetCheckDao.getReturnCd();

			if ("1".equals(returnCd)) {

				try { // status update(전표 헤더, 모바일카드리스트)
					String mngNoMobile = slipHeaderDao.getMngNoMobile();
					CardHistoryDao cardHistoryDao = new CardHistoryDao();
					cardHistoryDao.setMngNoMobile(mngNoMobile);
					cardHistoryDao.setStatus("2"); // 전송상태로 업데이트

					cardHistoryRepository.modifyCardHistoryDaoByMngNoMobile(cardHistoryDao);

					SlipHeaderDao slipHeaderDaoForStatus = new SlipHeaderDao();
					slipHeaderDaoForStatus.setMngNoMobile(mngNoMobile);
					slipHeaderDaoForStatus.setStatus("2");
					slipHeaderRepository.modifySlipHeaderDao(slipHeaderDaoForStatus);

					// 제안율 업데이트
					String empNo = slipHeaderDao.getWriteUser(); // 사번
					String vendorNm = slipDetailDao.getVendorNm(); // 가맹점명

					oraFunctionCallRepository.modifyAcntSuggestionByCompCdAndEmpNoAndVendorNmAndAcntCd(compCd, empNo,
							vendorNm, acntCd);

					// hibiz insert
					
					// table
					
					//function
					
					String expType ="A"; //경비 유형 A 법인카드
					oraFunctionCallRepository.saveSlipErpInterfaceByCompCdAndExpTypeAndMngNoMobile(compCd, expType, mngNoMobile);
					

					budgetCheckDao.setIsSuccess(true);

				} catch (Exception e) {
					budgetCheckDao.setIsSuccess(false);
				}

			} else {
				budgetCheckDao.setIsSuccess(false);
			}
		}

		return budgetCheckDao;

	}

	
}
