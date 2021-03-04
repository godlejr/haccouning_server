package com.hsys.ham.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hsys.ham.dao.CardHistoryDao;

@Repository
public interface CardHistoryRepository {

	List<CardHistoryDao> getCardHistoryDaosByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(
			@Param("status") String status, @Param("compCd") String compCd, @Param("empNo") String empNo,
			@Param("offset") long offset, @Param("limit") long limit);

	long saveCardHistoryDaoAndGetSeqno(CardHistoryDao cardHistoryDao);

	CardHistoryDao getCardHistoryDaoBySeqNo(@Param("seqNo") long seqNo);

	CardHistoryDao getCardHistoryDaoByMngNoMobile(@Param("mngNoMobile") String mngNoMobile);

	void modifyCardHistoryDao(CardHistoryDao cardHistoryDao);

	void modifyCardHistoryDaoByMngNoMobile(CardHistoryDao cardHistoryDao);

	void deleteCardHistoryDaoBySeqNo(@Param("seqNo") long seqNo);

	void deleteCardHistoryDaoByMngNoMobile(@Param("mngNoMobile") String mngNoMobile);

	Integer getCardHistoryDaoCountByStatusAndEmpNo(@Param("status") String status,@Param("empNo") String empNo);
}
