package com.hsys.ham.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hsys.ham.common.utils.JsonWebTokenUtil;
import com.hsys.ham.dao.UserDao;
import com.hsys.ham.repository.UserRepository;

/**
 * 유저 관련 서비스  클래스
 * 
 * 
 * @author 김동주 사원
 * 
 * @since 2019.03.27
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)		
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JsonWebTokenUtil jsonWebTokenUtil;
	
	@Value("#{jwt['jwt.tokenKey']}")
	private String tokenKey;
	
	/**
	 * 로그인 함수 
	 * 
	 * 성공시, 엑세스토큰이 담긴 User 반환 
	 * 실패시, null
	 * 
	 * @author 김동주 사원
	 * @since 2019.04.02
	 * 
	 * @param loginId
	 * @param loginPw
	 * 
	 * @return UserDao
	 * 
	 * @throws Exception
	 */
	@Override
	public UserDao getUserDaoByIdAndPassword(String loginId, String loginPw) throws Exception {
		UserDao userDao = userRepository.getUserDaoByIdAndPassword(loginId.trim(), loginPw.trim());
				
		if (userDao != null) {
			String comCd =userDao.getCompCd();
			String value = comCd + loginId + System.currentTimeMillis(); //시크릿 값 : 회사코드 + 아이디(사번)
			
			String accessToken = jsonWebTokenUtil.setTokenWithKeyAndValue(tokenKey, value);
			userDao.setAccessToken(accessToken);
		}

		return userDao;
	}

	
	/**
	 * 유저 편집 및 GET 함수 
	 * 
	 * 성공시, User update 반환 
	 * 실패시, null
	 * 
	 * @author 김동주 사원
	 * @since 2019.04.02
	 * 
	 * @param  userDao
	 * @return UserDao
	 * 
	 * @throws Exception
	 */
	@Override
	public UserDao editAndGetUserDao(UserDao userDao) throws Exception {
		userRepository.editUserDao(userDao);
		
		
		//primary key
		String compCd = userDao.getCompCd();
		String empNo = userDao.getEmpNo();
		
		UserDao modifiedUserDao = userRepository.getUserDaoByCompCdAndEmpNo(compCd,empNo);
		
		String comCd =userDao.getCompCd();
		String loginId = userDao.getLoginId();
		String value = comCd + loginId; //시크릿 값 : 회사코드 + 아이디(사번)
		
		String accessToken = jsonWebTokenUtil.setTokenWithKeyAndValue(tokenKey, value);
		modifiedUserDao.setAccessToken(accessToken);
		
		return modifiedUserDao;
	}

}
