package com.hsys.ham.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsys.ham.dao.CardHistoryDao;
import com.hsys.ham.dto.SmsTransferDto;
import com.hsys.ham.service.SmsService;

/**
 * SMS 처리 관련 컨트롤러 클래스
 * 
 * 
 * @author 김동주 사원
 * 
 * @since 2019.04.11
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	private SmsService smsService;
	
	/**
	 * Sms Luis engine 처리 및 DB 저장 API
	 * 
	 * @author 김동주 사원
	 * @since 2019.04.23
	 * 
	 * @param compCd
	 * @param empNo
	 * @param message
	 * @param compPhone
	 * 
	 * 
	 * @return CardHistoryDao
	 * 
	 * @throws Exception
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/getCardHistoryBySavingCardHistoryWithML.json", method = { RequestMethod.POST, RequestMethod.PUT })
	@ResponseBody
	public CardHistoryDao preprocessAndSaveAndGetCardHistoryDao(@RequestBody SmsTransferDto smsTransferDto ) throws Exception {
		
		return this.smsService.preprocessAndSaveAndGetCardHistoryDao(smsTransferDto);
	}
	
}
