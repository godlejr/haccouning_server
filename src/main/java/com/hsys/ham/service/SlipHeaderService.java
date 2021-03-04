package com.hsys.ham.service;

import java.util.List;

import com.hsys.ham.dao.BudgetCheckDao;
import com.hsys.ham.dao.SlipHeaderDao;

public interface SlipHeaderService {

	SlipHeaderDao getSlipHeaderDaoBySavingSlipHeaderAndSlipDetail(SlipHeaderDao slipHeaderDao) throws Exception;

	SlipHeaderDao getSlipHeaderDaoByMngNoMobile(String mngNoMobile) throws Exception;

	SlipHeaderDao getSlipHeaderDaoByModifyingSlipHeaderAndSlipDetail(SlipHeaderDao slipHeaderDao) throws Exception;

	BudgetCheckDao getBudgetCheckDaoByTransferringSlipHeaders(List<SlipHeaderDao> slipHeaderDaos) throws Exception;


}
