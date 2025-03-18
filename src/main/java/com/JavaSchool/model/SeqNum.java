package com.JavaSchool.model;

public class SeqNum {
    private String keyType;
    private int seqNo;
    private int seqMaxLength;

    public SeqNum(String keyType, int seqNo, int seqMaxLength) {
        this.keyType = keyType;
        this.seqNo = seqNo;
        this.seqMaxLength = seqMaxLength;
    }

    // Getters and Setters
    public String getKeyType() { return keyType; }
    public void setKeyType(String keyType) { this.keyType = keyType; }

    public int getSeqNo() { return seqNo; }
    public void setSeqNo(int seqNo) { this.seqNo = seqNo; }

    public int getSeqMaxLength() { return seqMaxLength; }
    public void setSeqMaxLength(int seqMaxLength) { this.seqMaxLength = seqMaxLength; }

    @Override
    public String toString() {
        return "SeqNum{" +
                "keyType='" + keyType + '\'' +
                ", seqNo=" + seqNo +
                ", seqMaxLength=" + seqMaxLength +
                '}';
    }
}
