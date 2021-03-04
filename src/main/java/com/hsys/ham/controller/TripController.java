package com.hsys.ham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsys.ham.dao.AcntDao;
import com.hsys.ham.dao.TripDao;
import com.hsys.ham.service.AcntService;
import com.hsys.ham.service.TripService;

@Controller
@RequestMapping("/trips")
public class TripController {

	@Autowired
	private TripService tripService;

	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getTripsByCompCdAndEmpNoAndDate.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public List<TripDao> getTripDaosByCompCdAndEmpNoAndDate(@RequestParam("compCd") String compCd,
			@RequestParam("empNo") String empNo, @RequestParam("date") String date) throws Exception {

		return this.tripService.getTripDaosByCompCdAndEmpNoAndDate(compCd, empNo, date);
	}

	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimit.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public List<TripDao> getTripDaosByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(@RequestParam("compCd") String compCd,
			@RequestParam("empNo") String empNo, @RequestParam("tripNm") String tripNm,
			@RequestParam("offset") long offset, @RequestParam("limit") long limit) throws Exception {

		return this.tripService.getTripDaosByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(compCd, empNo, tripNm, offset,
				limit);
	}

	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getTripByCompCdAndTripCdAndDeptCd.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public TripDao getTripDaoByCompCdAndTripCdAndDeptCd(@RequestParam("compCd") String compCd,
			@RequestParam("tripCd") String tripCd, @RequestParam("deptCd") String deptCd) throws Exception {

		return this.tripService.getTripDaoByCompCdAndTripCdAndDeptCd(compCd, tripCd, deptCd);
	}

	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getRecentOneTripByCompCdAndEmpNo.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public TripDao getRecentOneTripDaoByCompCdAndEmpNo(@RequestParam("compCd") String compCd,
			@RequestParam("empNo") String empNo) throws Exception {

		return this.tripService.getRecentOneTripDaoByCompCdAndEmpNo(compCd, empNo);
	}

}
