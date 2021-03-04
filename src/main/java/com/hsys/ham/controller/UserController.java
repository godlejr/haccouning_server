
package com.hsys.ham.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsys.ham.common.utils.AES256Util;
import com.hsys.ham.dao.UserDao;
import com.hsys.ham.service.UserService;


/**
 * 유저 관련 컨트롤러  클래스
 * 
 * 
 * @author 김동주 사원
 * 
 * @since 2019.03.27
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AES256Util aes256Util;
	
	@Autowired
	private UserService userService;
	
	@Value("#{encryption['encryption.aes.key']}")
	private String key;
	
	/**
	 * 로그인 API 
	 * 
	 * 성공시, 엑세스토큰이 담긴 User 반환 
	 * 실패시, null 반환
	 * 
	 * @author 김동주 사원
	 * @since 2019.04.01
	 * 
	 * @param id
	 * @param password
	 * @return UserDao
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login.json", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public UserDao getUserDaoByIdAndPassword(@RequestParam("loginId") String loginId,
			@RequestParam("loginPw") String loginPw) throws Exception {
		
		UserDao userDao = userService.getUserDaoByIdAndPassword(loginId, loginPw);
		
		if(userDao != null) {
			userDao.encrypt(aes256Util);
		}
		
		
		
		
		return userDao;
	}
	
	/**
	 * 유저 정보 수정 및 유저 반환API 
	 * 
	 * @author 김동주 사원
	 * @since 2019.04.02
	 * 
	 * @param UserDao
	 * @return UserDao
	 * 
	 * @throws Exception
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/edit", method = { RequestMethod.POST, RequestMethod.PUT })
	@ResponseBody
	public UserDao editAndGetUserDao(@RequestBody UserDao userDao) throws Exception {
		userDao.decrypt(aes256Util);

		UserDao userDaoForResponse =userService.editAndGetUserDao(userDao);		
		userDaoForResponse.encrypt(aes256Util);
		
		return userDaoForResponse;
	}
	
	
}
