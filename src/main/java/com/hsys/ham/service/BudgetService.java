package com.hsys.ham.service;

import java.util.List;

import com.hsys.ham.dao.BudgetDao;

public interface BudgetService {

	BudgetDao getBudgetDaoByCompCdAndDeptCdAndAcntCdAndPosDate(String compCd, String deptNo, String acntCd, String posDate) throws Exception;

	List<BudgetDao> getBudgetDaosByCompCdAndDeptCdAndAcntCdAndYearAndQuater(String compCd, String deptCd, String acntCd)  throws Exception;

}
