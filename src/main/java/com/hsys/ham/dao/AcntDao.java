package com.hsys.ham.dao;

public class AcntDao {
	private long rownum;
	private String acntCd;
	private String acntNm;
	private String acntRate;

	public AcntDao() {
		super();
	}

	public long getRownum() {
		return rownum;
	}

	public void setRownum(long rownum) {
		this.rownum = rownum;
	}

	public String getAcntCd() {
		return acntCd;
	}

	public void setAcntCd(String acntCd) {
		this.acntCd = acntCd;
	}

	public String getAcntNm() {
		return acntNm;
	}

	public void setAcntNm(String acntNm) {
		this.acntNm = acntNm;
	}

	public String getAcntRate() {
		return acntRate;
	}

	public void setAcntRate(String acntRate) {
		this.acntRate = acntRate;
	}
	
	

}
