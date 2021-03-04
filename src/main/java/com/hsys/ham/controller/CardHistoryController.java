package com.hsys.ham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsys.ham.dao.CardHistoryDao;
import com.hsys.ham.dao.SlipHeaderDao;
import com.hsys.ham.service.CardHistoryService;

/**
 * 카드 내역 관련 컨트롤러 클래스
 * 
 * 
 * @author 김동주 사원
 * 
 * @since 2019.03.27
 */
@Controller
@RequestMapping("/cardHistories")
public class CardHistoryController {

	@Autowired
	private CardHistoryService cardHistoryService;

	/**
	 * 카드 내역 조회 API
	 * 
	 * 회사코드, 사원번호, 상태값에 따른 카드 내역 조회
	 * 
	 * @author 김동주 사원
	 * @since 2019.04.05
	 * 
	 * @param compNo
	 * @param empNo
	 * @param status
	 * @param offset 페이징처리를 위한 첫번째 인덱스
	 * @param limit  페이징처리를 위한 마지막 인덱스
	 * 
	 * @return List<CardHistoryDao>
	 * 
	 * @throws Exception
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/{status}.json", method = RequestMethod.GET)
	@ResponseBody
	public List<CardHistoryDao> getCardHistoryDaosByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(
			@PathVariable("status") String status, @RequestParam("compCd") String compCd,
			@RequestParam("empNo") String empNo, @RequestParam("offset") long offset, @RequestParam("limit") long limit)
			throws Exception {
		return cardHistoryService
				.getCardHistoryDaosByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(status,
						compCd, empNo, offset, limit);
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/getCardHistoryByModifyingCardHistory.json", method = RequestMethod.POST)
	@ResponseBody
	public CardHistoryDao getCardHistoryDaoByModifyingCardHistoryDao(@RequestBody CardHistoryDao cardHistoryDao)
			throws Exception {
		return cardHistoryService.getCardHistoryDaoByModifyingCardHistoryDao(cardHistoryDao);
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/getIsSuccessByDeletingCardHistoriesAndSlipHeaders.json", method = RequestMethod.POST)
	@ResponseBody
	public Boolean getIsSuccessByDeletingCardHistoryDaosAndSlipHeaderDaos(
			@RequestBody List<CardHistoryDao> cardHistoryDaos) throws Exception {
		return cardHistoryService.getIsSuccessByDeletingCardHistoryDaosAndSlipHeaderDaos(cardHistoryDaos);
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/getIsSuccessByDeletingSlipHeadersAndModifyingCardHistories.json", method = RequestMethod.POST)
	@ResponseBody
	public Boolean getIsSuccessByDeletingSlipHeaderDaosAndModifyingCardHistoryDaos(
			@RequestBody List<CardHistoryDao> cardHistoryDaos) throws Exception {
		return cardHistoryService.getIsSuccessByDeletingSlipHeaderDaosAndModifyingCardHistoryDaos(cardHistoryDaos);
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/{status}/count.json", method = RequestMethod.GET)
	@ResponseBody
	public Integer getCardHistoryDaoCountByStatusAndEmpNo(@PathVariable("status") String status, @RequestParam("empNo")String empNo) throws Exception {
		return cardHistoryService.getCardHistoryDaoCountByStatusAndEmpNo(status, empNo);
	}

}
