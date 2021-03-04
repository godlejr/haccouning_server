package com.hsys.ham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hsys.ham.dao.TripDao;
import com.hsys.ham.repository.OraFunctionCallRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TripServiceImpl implements TripService {

	@Autowired
	private OraFunctionCallRepository oraFunctionCallRepository;

	@Override
	public List<TripDao> getTripDaosByCompCdAndEmpNoAndDate(String compCd, String empNo, String date) throws Exception {
		return this.oraFunctionCallRepository.getTripDaosByCompCdAndEmpNoAndDate(compCd, empNo, date);
	}

	@Override
	public List<TripDao> getTripDaosByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(String compCd, String empNo,
			String tripNm, long offset, long limit) throws Exception {
		return this.oraFunctionCallRepository.getTripDaosByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(compCd, empNo,
				tripNm, offset, limit);
	}

	@Override
	public TripDao getTripDaoByCompCdAndTripCdAndDeptCd(String compCd, String tripCd, String deptCd) throws Exception {
		return this.oraFunctionCallRepository.getTripDaoByCompCdAndTripCdAndDeptCd(compCd, tripCd, deptCd);
	}

	@Override
	public TripDao getRecentOneTripDaoByCompCdAndEmpNo(String compCd, String empNo) throws Exception {
		return this.oraFunctionCallRepository.getRecentOneTripDaoByCompCdAndEmpNo(compCd, empNo);
	}

}
