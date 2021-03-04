package com.hsys.ham.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hsys.ham.dao.AcntDao;
import com.hsys.ham.dao.BudgetCheckDao;
import com.hsys.ham.dao.BudgetDao;
import com.hsys.ham.dao.DeptDao;
import com.hsys.ham.dao.RegisteredCardDao;
import com.hsys.ham.dao.TripDao;

@Repository
public interface OraFunctionCallRepository {

	List<RegisteredCardDao> getRegisteredCardDaoByCompCdAndEmpNoAndCardNoAndCardCompCd(@Param("compCd") String compCd,
			@Param("empNo") String empNo, @Param("cardNo") String cardNo, @Param("cardCompCd") String cardCompCd);

	List<AcntDao> getAcntDaosWithSuggestionsByCompCdAndEmpNoAndVendorNm(@Param("compCd") String compCd,
			@Param("empNo") String empNo, @Param("vendorNm") String vendorNm);

	BudgetDao getBudgetDaoByCompCdAndDeptCdAndAcntCdAndYearAndQuater(@Param("compCd") String compCd,
			@Param("deptCd") String deptNo, @Param("acntCd") String acntCd, @Param("year") String year,
			@Param("quater") String quater);

	List<BudgetDao> getBudgetDaosByCompCdAndDeptCdAndAcntCdAndYearAndQuater(@Param("compCd") String compCd,
			@Param("deptCd") String deptNo, @Param("acntCd") String acntCd, @Param("year") String year,
			@Param("quater") String quater);

	List<AcntDao> getAcntDaosByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(@Param("compCd") String compCd,
			@Param("acntCd") String acntCd, @Param("acntNm") String acntNm, @Param("offset") long offset,
			@Param("limit") long limit);

	List<TripDao> getTripDaosByCompCdAndEmpNoAndDate(@Param("compCd") String compCd, @Param("empNo") String empNo,
			@Param("date") String date);

	List<TripDao> getTripDaosByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(@Param("compCd") String compCd,
			@Param("empNo") String empNo, @Param("tripNm") String tripNm, @Param("offset") long offset,
			@Param("limit") long limit);

	List<DeptDao> getDeptDaosByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(@Param("compCd") String compCd,
			@Param("empNo") String empNo, @Param("deptNm") String deptNm, @Param("offset") long offset,
			@Param("limit") long limit);

	TripDao getTripDaoByCompCdAndTripCdAndDeptCd(@Param("compCd") String compCd, @Param("tripCd") String tripCd,
			@Param("deptCd") String deptCd);

	BudgetCheckDao getBudgetCheckDaoByCompCdAndPosDateAndDeptCdAndAcntCdAndAmtTotOrTripCd(
			@Param("compCd") String compCd, @Param("posDate") String posDate, @Param("deptCd") String deptCd,
			@Param("acntCd") String acntCd, @Param("amtTot") String amtTot, @Param("tripCd") String tripCd);

	void modifyAcntSuggestionByCompCdAndEmpNoAndVendorNmAndAcntCd(@Param("compCd") String compCd,
			@Param("empNo") String empNo, @Param("vendorNm") String vendorNm, @Param("acntCd") String acntCd);

	void saveSlipErpInterfaceByCompCdAndExpTypeAndMngNoMobile(@Param("compCd") String compCd,
			@Param("expType") String expType, @Param("mngNoMobile") String mngNoMobile);

	TripDao getRecentOneTripDaoByCompCdAndEmpNo(@Param("compCd") String compCd, @Param("empNo") String empNo);

	void modifySlipErpInterface(@Param("mngNoMobile") String mngNoMobile);

}
