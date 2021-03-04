package com.hsys.ham.service;

import java.util.List;

import com.hsys.ham.dao.CardHistoryDao;
import com.hsys.ham.dao.SlipHeaderDao;

public interface CardHistoryService {

	List<CardHistoryDao> getCardHistoryDaosByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(
			String status, String compCd, String empNo, long offset, long limit) throws Exception;

	CardHistoryDao getCardHistoryDaoByModifyingCardHistoryDao(CardHistoryDao cardHistoryDao) throws Exception;

	Boolean getIsSuccessByDeletingCardHistoryDaosAndSlipHeaderDaos(List<CardHistoryDao> cardHistoryDaos)
			throws Exception;

	Boolean getIsSuccessByDeletingSlipHeaderDaosAndModifyingCardHistoryDaos(List<CardHistoryDao> cardHistoryDaos)
			throws Exception;


	Integer getCardHistoryDaoCountByStatusAndEmpNo(String status, String empNo)  throws Exception;

}
