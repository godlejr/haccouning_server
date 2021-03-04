package com.hsys.ham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsys.ham.dao.BudgetDao;
import com.hsys.ham.service.BudgetService;

@Controller
@RequestMapping("/budgets")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;

	@Secured("ROLE_USER")
	@RequestMapping(value = "/getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate.json", method = RequestMethod.GET)
	@ResponseBody
	public BudgetDao getBudgetDaoByCompCdAndDeptCdAndAcntCdAndYearAndQuater(@RequestParam("compCd") String compCd,
			@RequestParam("deptCd") String deptCd, @RequestParam("acntCd") String acntCd,@RequestParam("posDate") String posDate ) throws Exception {

		return budgetService.getBudgetDaoByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/getBudgetsByCompCdAndDeptCdAndAcntCd.json", method = RequestMethod.GET)
	@ResponseBody
	public List<BudgetDao> getBudgetDaosByCompCdAndDeptCdAndAcntCdAndYearAndQuater(@RequestParam("compCd") String compCd,
			@RequestParam("deptCd") String deptCd, @RequestParam("acntCd") String acntCd) throws Exception {

		return budgetService.getBudgetDaosByCompCdAndDeptCdAndAcntCdAndYearAndQuater(compCd, deptCd, acntCd);
	}
	
	
	
}
