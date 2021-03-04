package com.hsys.ham.dao;

public class DeptDao {
	private long rownum;
	
	
	
	private String expObjmCd;
	private String objmNm;

	public DeptDao() {
		super();
	}

	public long getRownum() {
		return rownum;
	}

	public void setRownum(long rownum) {
		this.rownum = rownum;
	}

	public String getExpObjmCd() {
		return expObjmCd;
	}

	public void setExpObjmCd(String expObjmCd) {
		this.expObjmCd = expObjmCd;
	}

	public String getObjmNm() {
		return objmNm;
	}

	public void setObjmNm(String objmNm) {
		this.objmNm = objmNm;
	}

}
