package com.JavaSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaSchool.DTO.TransactionRequest;
import com.JavaSchool.model.InternalMessage;

import util.ReferenceNoGenerator;

@Service
public class JsonToInternalService {

    private final ReferenceNoGenerator referenceNoGenerator;
    private static final String BLANKS = "";
    
    @Autowired
    public JsonToInternalService(ReferenceNoGenerator referenceNoGenerator) {
        this.referenceNoGenerator = referenceNoGenerator;
    }
	
    public InternalMessage convertJsonToInternalMessage(TransactionRequest request) {
        String referenceNo = referenceNoGenerator.generateReferenceNo("RRNO");
        return new InternalMessage(
                "PGW",                         // Default value
                BLANKS,                        // TransactionStatus set to blank
                referenceNo,                   // Auto-generated reference number
                request.getCardNo(),
                request.getTransactionDate(),
                request.getMerchantId(),
                request.getTerminalId(),
                request.getSystemAuditTrace(),
                request.getAmount(),
                request.getCurrency(),
                BLANKS,
                request.getDateTime()
        );
    }
}