package com.hsys.ham.service;

import java.util.List;

import com.hsys.ham.dao.TripDao;

public interface TripService {

	List<TripDao> getTripDaosByCompCdAndEmpNoAndDate(String compCd, String empNo, String date) throws Exception;

	List<TripDao> getTripDaosByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(String compCd, String empNo, String tripNm,
			long offset, long limit) throws Exception;

	TripDao getTripDaoByCompCdAndTripCdAndDeptCd(String compCd, String tripCd, String deptCd) throws Exception;

	TripDao getRecentOneTripDaoByCompCdAndEmpNo(String compCd, String empNo) throws Exception;
}
