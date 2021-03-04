package com.hsys.ham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hsys.ham.dao.AcntDao;
import com.hsys.ham.repository.OraFunctionCallRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)		
public class AcntServiceImpl implements AcntService {

	@Autowired
	private OraFunctionCallRepository oraFunctionCallRepository;
	
	@Override
	public List<AcntDao> getAcntDaosWithSuggestionsByCompCdAndEmpNoAndVendorNm(String compCd, String empNo, String vendorNm)
			throws Exception {
		
		return this.oraFunctionCallRepository.getAcntDaosWithSuggestionsByCompCdAndEmpNoAndVendorNm(compCd, empNo, vendorNm);
	}

	@Override
	public List<AcntDao> getAcntDaosByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(String compCd, String acntCd,
			String acntNm, long offset, long limit) throws Exception {
		return this.oraFunctionCallRepository.getAcntDaosByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(compCd, acntCd, acntNm,offset ,limit);
	}

}
