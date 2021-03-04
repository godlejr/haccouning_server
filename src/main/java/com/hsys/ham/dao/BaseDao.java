package com.hsys.ham.dao;

import java.io.Serializable;

/**
 * DAO 공통 클래스
 * 
 * 
 * @author 김동주 사원
 * @since 2019.03.28
 * 
 */
public abstract class BaseDao implements Serializable{
	private String cdate;
	private String udate;

	public BaseDao() {
		super();
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getUdate() {
		return udate;
	}

	public void setUdate(String udate) {
		this.udate = udate;
	}

}
