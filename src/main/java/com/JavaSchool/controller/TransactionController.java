package com.JavaSchool.controller;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaSchool.DTO.TransactionRequest;
import com.JavaSchool.DTO.TransactionResponse;
import com.JavaSchool.service.CommChannel;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private CommChannel commChannel;
        
    @GetMapping("/")  // Handles GET requests to http://localhost:8080/api/
    public static ResponseEntity<Object> generateResponse() {
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "Welcome to the Java School Project.";
        String greeting = "Hello!";
            map.put("message", message);
            map.put("greeting", greeting);
            return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public CompletableFuture<ResponseEntity<TransactionResponse>> processTransaction(@RequestBody TransactionRequest request) {
        System.out.println(LocalTime.now() + " Controller > Request message: [" + request + "]");
        CompletableFuture<TransactionResponse> responseMessage = commChannel.processMessage(request);
        return responseMessage
        	.thenApply(response -> ResponseEntity.ok(response))
            .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    
}
/*
	return responseMessage
        .thenApply(new Function<TransactionResponse, ResponseEntity<TransactionResponse>>() {
            @Override
            public ResponseEntity<TransactionResponse> apply(TransactionResponse response) {
                return ResponseEntity.ok(response);
            }
        })
        .exceptionally(new Function<Throwable, ResponseEntity<TransactionResponse>>() {
            @Override
            public ResponseEntity<TransactionResponse> apply(Throwable ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
*/




