package com.hsys.ham.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hsys.ham.dao.BudgetDao;
import com.hsys.ham.repository.OraFunctionCallRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BudgetServiceImpl implements BudgetService {

	@Autowired
	private OraFunctionCallRepository oraFunctionCallRepository;

	@Override
	public BudgetDao getBudgetDaoByCompCdAndDeptCdAndAcntCdAndPosDate(String compCd, String deptCd, String acntCd,
			String posDate) throws Exception {

		int year = Integer.parseInt(posDate.substring(0, 4));
		int month = Integer.parseInt(posDate.substring(4, 6));

		int quater = (int) Math.ceil(month / 3.0);

		System.out.println(deptCd + " " + acntCd + " " + year + "dd" + month + "예산1" + quater);
		return oraFunctionCallRepository.getBudgetDaoByCompCdAndDeptCdAndAcntCdAndYearAndQuater(compCd, deptCd, acntCd,
				year + "", quater + "");
	}

	@Override
	public List<BudgetDao> getBudgetDaosByCompCdAndDeptCdAndAcntCdAndYearAndQuater(String compCd, String deptCd,
			String acntCd) throws Exception {

		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		int quater = (int) Math.ceil(month / 3.0);

		if ("all".equals(acntCd)) {
			acntCd = "";
		}

		System.out.println(deptCd + " " + acntCd + " " + year + "dd" + month + "예산" + quater);

		return oraFunctionCallRepository.getBudgetDaosByCompCdAndDeptCdAndAcntCdAndYearAndQuater(compCd, deptCd, acntCd,
				year + "", quater + "");
	}

}
