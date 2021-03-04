package com.hsys.ham.dao;

public class RegisteredCardDao {
	private String empNo;
	private String cardNo;
	private String localBrand;
	private String validity;

	public RegisteredCardDao() {
		super();
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getLocalBrand() {
		return localBrand;
	}

	public void setLocalBrand(String localBrand) {
		this.localBrand = localBrand;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

}
