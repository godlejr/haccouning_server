package com.hsys.ham.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hsys.ham.dao.CardCompDao;

@Repository
public interface CardCompRepository {

	CardCompDao getCardCompDaoByCompPhone(@Param("compPhone") String compPhone);

}
