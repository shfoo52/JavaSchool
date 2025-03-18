package com.JavaSchool.model;

import java.sql.Timestamp;

public class SRLog {
    private String referenceNo;
    private String source;
    private int systemAuditTrace;
    private String transactionStatus;
    private Timestamp dateTime;
    private String message;
    private Timestamp dateTimeOut;
    private String messageOut;

    public SRLog() {
        this.referenceNo = "";
        this.source = "";
        this.systemAuditTrace = 0;
        this.transactionStatus = "";
        this.dateTime = null;
        this.message = "";
        this.dateTimeOut = null;
        this.messageOut = "";
	}

    public SRLog(String referenceNo, String source, int systemAuditTrace, String transactionStatus, 
    		Timestamp dateTime, String message, Timestamp dateTimeOut, String messageOut) {
        this.referenceNo = referenceNo;
        this.source = source;
        this.systemAuditTrace = systemAuditTrace;
        this.transactionStatus = transactionStatus;
        this.dateTime = dateTime;
        this.message = message;
        this.dateTimeOut = dateTimeOut;
        this.messageOut = messageOut;
    }

	// Getters and Setters
    public String getReferenceNo() { return referenceNo; }
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

	public int getSystemAuditTrace() { return systemAuditTrace; }
    public void setSystemAuditTrace(int systemAuditTrace) { this.systemAuditTrace = systemAuditTrace; }

    public String getTransactionStatus() { return transactionStatus; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }

    public Timestamp getDateTime() { return dateTime; }
    public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Timestamp getDateTimeOut() { return dateTimeOut; }
	public void setDateTimeOut(Timestamp dateTimeOut) { this.dateTimeOut = dateTimeOut; }

	public String getMessageOut() { return messageOut; }
	public void setMessageOut(String messageOut) { this.messageOut = messageOut; }
}
