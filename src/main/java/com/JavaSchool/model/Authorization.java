package com.JavaSchool.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Authorization {
    private long cardNo;
    private Date transactionDate;
    private int merchantID;
    private String terminalID;
    private String approvalCode;
    private String referenceNo;
    private int systemAuditTrace;
    private double amount;
    private String currency;
    private Timestamp dateTime;
    private String transactionStatus;

    // Constructor
    public Authorization(long cardNo, Date transactionDate, int merchantID, String terminalID, String approvalCode,
                         String referenceNo, int systemAuditTrace, double amount, String currency, Timestamp dateTime,
                         String transactionStatus) {
        this.cardNo = cardNo;
        this.transactionDate = transactionDate;
        this.merchantID = merchantID;
        this.terminalID = terminalID;
        this.approvalCode = approvalCode;
        this.referenceNo = referenceNo;
        this.systemAuditTrace = systemAuditTrace;
        this.amount = amount;
        this.currency = currency;
        this.dateTime = dateTime;
        this.transactionStatus = transactionStatus;
    }

	public Authorization() {
	}

	// Getters and Setters
    public long getCardNo() {
        return cardNo;
    }

    public void setCardNo(long cardNo) {
        this.cardNo = cardNo;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public int getSystemAuditTrace() {
        return systemAuditTrace;
    }

    public void setSystemAuditTrace(int systemAuditTrace) {
        this.systemAuditTrace = systemAuditTrace;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
}
