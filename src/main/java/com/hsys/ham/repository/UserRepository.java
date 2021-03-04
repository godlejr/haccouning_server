package com.hsys.ham.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hsys.ham.dao.UserDao;

@Repository
public interface UserRepository {

	UserDao getUserDaoByIdAndPassword(@Param("loginId") String loginId, @Param("loginPw") String loginPw);

	void editUserDao(UserDao userDao);

	UserDao getUserDaoByCompCdAndEmpNo(@Param("compCd") String compCd, @Param("empNo") String empNo);


}
