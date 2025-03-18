package com.JavaSchool.model;

import java.sql.Date;
import java.sql.Timestamp;

public class InternalMessage {
    private String source;
    private String transactionStatus;
    private String referenceNo;
    private long cardNo;
    private Date transactionDate;
    private int merchantId;
    private String terminalId;
    private int systemAuditTrace;
    private double amount;
    private String currency;
    private String approvalCode;
    private Timestamp dateTime;

    // Constructor
	public InternalMessage(String source, String transactionStatus, String referenceNo, long cardNo, Date transactionDate,
			int merchantId, String terminalId, int systemAuditTrace, double amount, String currency, String approvalCode, 
			Timestamp dateTime) {
		this.source = source;
	    this.transactionStatus = transactionStatus;
	    this.referenceNo = referenceNo;
	    this.cardNo = cardNo;
	    this.transactionDate = transactionDate;
	    this.merchantId = merchantId;
	    this.terminalId = terminalId;
	    this.systemAuditTrace = systemAuditTrace;
	    this.amount = amount;
	    this.currency = currency;
	    this.approvalCode = approvalCode;
	    this.dateTime = dateTime;
	}

	// Getters and Setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

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

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
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

    public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

	@Override
    public String toString() {
        return String.format("%-10s%-5s%-12s%016d%8s%08d%8s%06d%012.2f%3s%6s%19s",
                source,
                transactionStatus,
                referenceNo,
                cardNo,
                transactionDate,
                merchantId,
                terminalId,
                systemAuditTrace,
                amount,
                currency,
                approvalCode,
                dateTime);
    }
}