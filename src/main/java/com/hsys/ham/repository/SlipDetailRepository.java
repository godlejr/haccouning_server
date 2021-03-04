package com.hsys.ham.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hsys.ham.dao.SlipDetailDao;

@Repository
public interface SlipDetailRepository {

	void saveSlipDetailDaos(List<SlipDetailDao> slipDetails);

	void modifySlipDetailDaos(List<SlipDetailDao> slipDetails);

	void deleteSlipDetailDaoByMngNoMobile(@Param("mngNoMobile") String mngNoMobile);
}
