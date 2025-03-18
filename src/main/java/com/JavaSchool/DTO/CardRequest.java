package com.JavaSchool.DTO;

public class CardRequest {
    private String cardNo; // ✅ Ensure this matches the JSON field name

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}