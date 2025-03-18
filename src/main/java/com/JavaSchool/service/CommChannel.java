package com.JavaSchool.service;

import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.JavaSchool.DTO.TransactionRequest;
import com.JavaSchool.DTO.TransactionResponse;
import com.JavaSchool.model.InternalMessage;

@Service
public class CommChannel {

    @Autowired
    private BlockingQueue<InternalMessage> messageQueue;

    @Autowired
    private BlockingQueue<InternalMessage> subRouterResponseQueue;

    @Autowired
    private JsonToInternalService converter;
    
    @Async
    public CompletableFuture<TransactionResponse> processMessage(TransactionRequest request) {
        CompletableFuture<TransactionResponse> futureResponse = new CompletableFuture<>();
        System.out.println(LocalTime.now() + " Comm Service " + Thread.currentThread().getName() + " started.");
        try {
            System.out.println(LocalTime.now() + " Comm > Request message (INPUT): [" + request + "]");
            InternalMessage internalMessage = converter.convertJsonToInternalMessage(request);

            messageQueue.put(internalMessage); 
            System.out.println(LocalTime.now() + " Comm > Request message (OUTPUT): [" + internalMessage + "]");

            InternalMessage responseMessage = subRouterResponseQueue.take();  // Wait for response
            System.out.println(LocalTime.now() + " Comm > Response message: [" + responseMessage + "]");

            // Convert responseMessage back to original JSON format
            TransactionResponse transactionResponse = convertToTransactionResponse(responseMessage);
            futureResponse.complete(transactionResponse);
        
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return futureResponse; // Client waits for this future
    }    
         
    private TransactionResponse convertToTransactionResponse(InternalMessage responseMessage) {
        
        return new TransactionResponse(
                responseMessage.getCardNo(),
                responseMessage.getTransactionDate(),
                responseMessage.getMerchantId(),
                responseMessage.getTerminalId(),
                responseMessage.getSystemAuditTrace(),
                responseMessage.getAmount(),
                responseMessage.getCurrency(),
                responseMessage.getReferenceNo(),
                responseMessage.getApprovalCode(),
                mapResponseCode(responseMessage.getTransactionStatus())
        );
    }
    
    private String mapResponseCode(String transactionStatus) {
        final String APPROVED = "00";
        final String DONOTHONOR = "05";
    	
    	switch (transactionStatus) {
    		case "/F000":
    			return APPROVED;
    		default:
    			return DONOTHONOR;
    	}	
    }
}