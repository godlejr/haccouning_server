package com.hsys.ham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsys.ham.dao.DeptDao;
import com.hsys.ham.service.DeptService;

@Controller
@RequestMapping("/depts")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public List<DeptDao> getDeptDaosByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(@RequestParam("compCd") String compCd,
			@RequestParam("empNo") String empNo, @RequestParam("deptNm") String deptNm,
			@RequestParam("offset") long offset, @RequestParam("limit") long limit) throws Exception {

		return this.deptService.getDeptDaosByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(compCd, empNo, deptNm, offset,
				limit);
	}
}
