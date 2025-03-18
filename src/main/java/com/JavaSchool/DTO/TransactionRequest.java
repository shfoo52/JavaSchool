package com.JavaSchool.DTO;

import java.sql.Date;
import java.sql.Timestamp;

public class TransactionRequest {
    private long cardNo;
    private Date transactionDate;
    private int merchantId;
    private String terminalId;
    private int systemAuditTrace;
    private double amount;
    private String currency;
    private Timestamp dateTime;

    // Constructor
    public TransactionRequest(long cardNo, Date transactionDate, int merchantId, String terminalId,
                              int systemAuditTrace, double amount, String currency, Timestamp dateTime) {
        this.cardNo = cardNo;
        this.transactionDate = transactionDate;
        this.merchantId = merchantId;
        this.terminalId = terminalId;
        this.systemAuditTrace = systemAuditTrace;
        this.amount = amount;
        this.currency = currency;
        this.dateTime = dateTime;
    }
    
    // Getters and Setters
    public long getCardNo() { return cardNo; }
    public void setCardNo(long cardNo) { this.cardNo = cardNo; }

    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }

    public int getMerchantId() { return merchantId; }
    public void setMerchantId(int merchantId) { this.merchantId = merchantId; }

    public String getTerminalId() { return terminalId; }
    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }

    public int getSystemAuditTrace() { return systemAuditTrace; }
    public void setSystemAuditTrace(int systemAuditTrace) { this.systemAuditTrace = systemAuditTrace; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Timestamp getDateTime() { return dateTime; }
    public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }
    
    @Override
    public String toString() {
        return "TransactionRequest{" +
                "cardNo=" + cardNo +
                ", transactionDate='" + transactionDate + '\'' +
                ", merchantId=" + merchantId +
                ", terminalId='" + terminalId + '\'' +
                ", systemAuditTrace=" + systemAuditTrace +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
    
}