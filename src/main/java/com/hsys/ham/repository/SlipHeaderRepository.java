package com.hsys.ham.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hsys.ham.dao.SlipHeaderDao;

@Repository
public interface SlipHeaderRepository {

	void saveSlipHeaderDaoAndGetMngNoMobile(SlipHeaderDao slipHeaderDao);

	SlipHeaderDao getSlipHeaderDaoByMngNoMobile(@Param("mngNoMobile") String mngNoMobile);

	void modifySlipHeaderDao(SlipHeaderDao slipHeaderDao);
	
	void deleteSlipHeaderDaoByMngNoMobile(@Param("mngNoMobile") String mngNoMobile);


}
