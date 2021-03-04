package com.hsys.ham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsys.ham.dao.DeptDao;
import com.hsys.ham.repository.OraFunctionCallRepository;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private OraFunctionCallRepository oraFunctionCallRepository;

	@Override
	public List<DeptDao> getDeptDaosByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(String compCd, String empNo,
			String deptNm, long offset, long limit) throws Exception {

		System.out.println("dddddd::" + deptNm);
		return this.oraFunctionCallRepository.getDeptDaosByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(compCd, empNo,
				deptNm, offset, limit);

	}

}
