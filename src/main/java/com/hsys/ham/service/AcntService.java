package com.hsys.ham.service;

import java.util.List;

import com.hsys.ham.dao.AcntDao;

public interface AcntService {

	List<AcntDao> getAcntDaosWithSuggestionsByCompCdAndEmpNoAndVendorNm(String compCd, String empNo, String vendorNm)
			throws Exception;

	List<AcntDao> getAcntDaosByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(String compCd, String acntCd, String acntNm,
			long offset, long limit) throws Exception;
}
