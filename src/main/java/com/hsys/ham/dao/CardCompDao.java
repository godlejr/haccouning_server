package com.hsys.ham.dao;

public class CardCompDao extends BaseDao {
	private String compCd;
	private String compNm;
	private String compPhone;

	public CardCompDao() {
		super();
	}

	public String getCompCd() {
		return compCd;
	}

	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}

	public String getCompNm() {
		return compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getCompPhone() {
		return compPhone;
	}

	public void setCompPhone(String compPhone) {
		this.compPhone = compPhone;
	}

}
