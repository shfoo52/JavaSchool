package com.JavaSchool.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Card {
    private long cardNo;
    private long accountNo;
    private short stopCode;
    private short cardStatus;
    private int expiryDate;  // YYYYMM
    private char activationFlag;
    private Date activationDate;
    private Timestamp dateTime;

    public Card(long cardNo, long accountNo, short stopCode, short cardStatus,
                int expiryDate, char activationFlag, Date activationDate, Timestamp dateTime) {
        this.cardNo = cardNo;
        this.accountNo = accountNo;
        this.stopCode = stopCode;
        this.cardStatus = cardStatus;
        this.expiryDate = expiryDate;
        this.activationFlag = activationFlag;
        this.activationDate = activationDate;
        this.dateTime = dateTime;
    }

    public Card() {
        this.cardNo = 0;
        this.accountNo = 0;
        this.stopCode = 0;
        this.cardStatus = 0;
        this.expiryDate = 0;
        this.activationFlag = ' ';  // represent char
        this.activationDate =  null;
        this.dateTime = null;
    }

	// Getters and Setters
    public long getCardNo() { return cardNo; }
    public void setCardNo(long cardNo) { this.cardNo = cardNo; }

    public long getAccountNo() { return accountNo; }
    public void setAccountNo(long accountNo) { this.accountNo = accountNo; }

    public short getStopCode() { return stopCode; }
    public void setStopCode(short stopCode) { this.stopCode = stopCode; }

    public short getCardStatus() { return cardStatus; }
    public void setCardStatus(short cardStatus) { this.cardStatus = cardStatus; }

    public int getExpiryDate() { return expiryDate; }
    public void setExpiryDate(int expiryDate) { this.expiryDate = expiryDate; }

    public char getActivationFlag() { return activationFlag; }
    public void setActivationFlag(char activationFlag) { this.activationFlag = activationFlag; }

    public Date getActivationDate() { return activationDate; }
    public void setActivationDate(Date activationDate) { this.activationDate = activationDate; }

    public Timestamp getDateTime() { return dateTime; }
    public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }
}
