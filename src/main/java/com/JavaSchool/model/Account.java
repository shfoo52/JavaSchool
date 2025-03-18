package com.JavaSchool.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Account {
    private long accountNo;
    private int creditLimit;
    private short agreementStatus;
    private Date openingDate;
    private double currentBalance;
    private Timestamp dateTime;
    
    // constructor
	public Account(long accountNo, int creditLimit, short agreementStatus, Date openingDate, double currentBalance,
			Timestamp dateTime) {
		this.accountNo = accountNo;
		this.creditLimit = creditLimit;
		this.agreementStatus = agreementStatus;
		this.openingDate = openingDate;
		this.currentBalance = currentBalance;
		this.dateTime = dateTime;
	}
	public Account() {
		this.accountNo = 0;
		this.creditLimit = 0;
		this.agreementStatus = 0;
		this.openingDate = null;
		this.currentBalance = 0;
		this.dateTime = null;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public int getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	public short getAgreementStatus() {
		return agreementStatus;
	}
	public void setAgreementStatus(short agreementStatus) {
		this.agreementStatus = agreementStatus;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

}