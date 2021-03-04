package com.hsys.ham.service;

import java.util.List;

import com.hsys.ham.dao.DeptDao;

public interface DeptService {

	List<DeptDao> getDeptDaosByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(String compCd, String empNo, String deptNm,
			long offset, long limit) throws Exception;

}
