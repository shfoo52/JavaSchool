package com.JavaSchool.service;

import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaSchool.dao.SRLogDAO;
import com.JavaSchool.model.SRLog;
import com.JavaSchool.model.InternalMessage;

import jakarta.annotation.PostConstruct;

@Service
public class SubRouter {

    @Autowired
    private BlockingQueue<InternalMessage> messageQueue;

    @Autowired
    private BlockingQueue<InternalMessage> authorizationQueue; // Queue for AuthorizationService Request

    @Autowired
    private BlockingQueue<InternalMessage> authorizationResponseQueue; // Queue for AuthorizationService Response
    
    @Autowired
    private BlockingQueue<InternalMessage> subRouterResponseQueue; // Queue for AuthorizationService Response
    
    @Autowired
    private ExecutorService subrouterThreadPool;

    @Autowired
    private SRLogDAO srLogDAO;  // DAO to save data into SRLog table
    
    @Autowired
    private SRLog srLog;
    
    @PostConstruct
    public void startSubrouter() {
        for (int i = 0; i < 3; i++) {
            subrouterThreadPool.submit(this::consumeMessage);
            // 🔹 Start response consumer thread
            subrouterThreadPool.submit(this::processResponse);
        }
    }

    private void consumeMessage() {
        System.out.println(LocalTime.now() + " Sub-Router Resquest Service " + Thread.currentThread().getName() + " started.");
        while (true) {
            try {
            	InternalMessage message = messageQueue.take(); // 🔹 Get request
                System.out.println(LocalTime.now() + " Subrouter > Request message: [" + message + "]");

                saveSRLog(message);
                // Send message to AuthorizationService via BlockingQueue
                authorizationQueue.put(message);
                System.out.println(LocalTime.now() + " Subrouter > Forwarded message to AuthorizationService");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private void processResponse() {
       System.out.println(LocalTime.now() + " Sub-Router Response Service " + Thread.currentThread().getName() + " started.");
       while (true) {
            try {
                InternalMessage responseMessage = authorizationResponseQueue.take(); // 🔹 Get response
                System.out.println(LocalTime.now() + " SubRouter > Received response: [" + responseMessage + "]");

                updateSRLog(responseMessage);

                // Send message to AuthorizationService via BlockingQueue
                subRouterResponseQueue.put(responseMessage);
                System.out.println(LocalTime.now() + " Subrouter > Response message to Comm.");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private void saveSRLog(InternalMessage message) {
        srLog.setReferenceNo(message.getReferenceNo());
        srLog.setSource(message.getSource());
        srLog.setSystemAuditTrace(message.getSystemAuditTrace());
        srLog.setTransactionStatus(message.getTransactionStatus());
        srLog.setDateTime(message.getDateTime());
        srLog.setMessage(message.toString());
        
        srLogDAO.insertSRLog(srLog);
    }
    
    private void updateSRLog(InternalMessage responseMessage) {
        srLogDAO.updateSRLogResponse(responseMessage.getReferenceNo(), 
        		responseMessage.getTransactionStatus(), responseMessage);;
    }
}