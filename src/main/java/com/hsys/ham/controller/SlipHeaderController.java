package com.hsys.ham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsys.ham.dao.BudgetCheckDao;
import com.hsys.ham.dao.CardHistoryDao;
import com.hsys.ham.dao.SlipHeaderDao;
import com.hsys.ham.service.SlipHeaderService;

/**
 * 전표 처리 관련 컨트롤러 클래스
 * 
 * 
 * @author 김동주 사원
 * 
 * @since 2019.04.29
 */
@Controller
@RequestMapping("/slipHeaders")
public class SlipHeaderController {
	
	
	@Autowired
	private SlipHeaderService slipHeaderService;
	
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getSlipHeaderBySavingSlipHeaderAndSlipDetail.json", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public SlipHeaderDao getSlipHeaderDaoBySavingSlipHeaderAndSlipDetail(@RequestBody SlipHeaderDao slipHeaderDao) throws Exception {

		
		return slipHeaderService.getSlipHeaderDaoBySavingSlipHeaderAndSlipDetail(slipHeaderDao);
	}
	
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getSlipHeaderByMngNoMobile.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public SlipHeaderDao getSlipHeaderDaoByMngNoMobile(@RequestParam("mngNoMobile") String mngNoMobile) throws Exception {

		
		return slipHeaderService.getSlipHeaderDaoByMngNoMobile(mngNoMobile);
	}
	
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getSlipHeaderByModifyingSlipHeaderAndSlipDetail.json", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public SlipHeaderDao getSlipHeaderDaoByModifyingSlipHeaderAndSlipDetail(@RequestBody SlipHeaderDao slipHeaderDao) throws Exception {

		
		return slipHeaderService.getSlipHeaderDaoByModifyingSlipHeaderAndSlipDetail(slipHeaderDao);
	}
	
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/getBudgetCheckByTransferringSlipHeaders.json", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public BudgetCheckDao getBudgetCheckDaoByTransferringSlipHeaders(@RequestBody List<SlipHeaderDao> slipHeaderDaos) throws Exception {

		return slipHeaderService.getBudgetCheckDaoByTransferringSlipHeaders(slipHeaderDaos);
	}
	
	
	
}
