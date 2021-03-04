package com.hsys.ham.service;

import com.hsys.ham.dao.UserDao;

public interface UserService {

	UserDao getUserDaoByIdAndPassword(String loginId, String loginPw) throws Exception;

	UserDao editAndGetUserDao(UserDao userDao) throws Exception;

}
