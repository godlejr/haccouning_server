package com.hsys.ham.dao;

public class BudgetCheckDao {
	private String returnCd;
	private Integer budgetAmt;
	private String returnMsg;
	private Boolean isSuccess;

	public BudgetCheckDao() {
		super();
	}

	public String getReturnCd() {
		return returnCd;
	}

	public void setReturnCd(String returnCd) {
		this.returnCd = returnCd;
	}

	public Integer getBudgetAmt() {
		return budgetAmt;
	}

	public void setBudgetAmt(Integer budgetAmt) {
		this.budgetAmt = budgetAmt;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	

}
