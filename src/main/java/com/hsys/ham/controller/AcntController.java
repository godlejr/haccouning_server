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
import com.hsys.ham.service.AcntService;

@Controller
@RequestMapping("/acnts")
public class AcntController {

	@Autowired
	private AcntService acntService;

	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public List<AcntDao> getAcntDaosWithSuggestionsByCompCdAndEmpNoAndVendorNm(@RequestParam("compCd") String compCd,
			@RequestParam("empNo") String empNo, @RequestParam("vendorNm") String vendorNm) throws Exception {

		return this.acntService.getAcntDaosWithSuggestionsByCompCdAndEmpNoAndVendorNm(compCd, empNo, vendorNm);
	}
	
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public List<AcntDao> getAcntDaosByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(@RequestParam("compCd") String compCd,
			@RequestParam("acntCd") String acntCd, @RequestParam("acntNm") String acntNm, @RequestParam("offset") long offset,
			@RequestParam("limit") long limit) throws Exception {

		return this.acntService.getAcntDaosByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(compCd, acntCd, acntNm,offset ,limit);
	}
	
	
}
