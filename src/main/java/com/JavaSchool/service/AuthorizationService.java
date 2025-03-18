package com.JavaSchool.service;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaSchool.dao.AccountDAO;
import com.JavaSchool.dao.AuthorizationDAO;
import com.JavaSchool.dao.CardDAO;
import com.JavaSchool.model.Account;
import com.JavaSchool.model.Authorization;
import com.JavaSchool.model.Card;
import com.JavaSchool.model.InternalMessage;

import jakarta.annotation.PostConstruct;

@Service
public class AuthorizationService {
    
    @Autowired
    private BlockingQueue<InternalMessage> authorizationQueue;  // 🔹Request Queue

    @Autowired
    private BlockingQueue<InternalMessage> authorizationResponseQueue; // 🔹Response Queue

    @Autowired
    private ExecutorService authorizationThreadPool;
    
    @Autowired
    private CardDAO cardDAO;
    
    @Autowired
    private AccountDAO accountDAO;
    
    @Autowired
    private AuthorizationDAO authorizationDAO;

    @Autowired
    private Account account;
    
    @Autowired
    private Card card;
    
    @Autowired
    private Authorization authorization;
   
    private static final String GOOD = "/F000";
    private static final String CARDNOTFOUND = ".F100";
    private static final String CARDSTOPPED = ".F001";
    private static final String CARDNOTEMBOSSED = ".F002";
    private static final String CARDNOTACTIVATED = ".F003";
    private static final String ACCOUNTNOTFOUND = ".F200";
    private static final String ACCOUNTINACTIVE = ".F004";
    private static final String ACCOUNTOVERLIMIT = ".F005";
    private static final String BLANKS = "";
    private static final String RESET  = "\u001B[0m";
    private static final String RED    = "\u001B[31m";
    private static final String GREEN  = "\u001B[32m";
    private static final String BG_YELLOW = "\u001B[43m";
    private boolean isApproved;
    private String AuthResult;
    private String colorCode; 
    
    @PostConstruct
    public void startAuthorizationTasks() {
        for (int i = 0; i < 3; i++) {
            authorizationThreadPool.submit(this::processAuthorization);
        }
    }
    
    private void processAuthorization() {
        System.out.println(LocalTime.now() + " Authorization Service " + Thread.currentThread().getName() + " started.");
        while (true) {
            try {
                InternalMessage message = authorizationQueue.take();
                System.out.println(LocalTime.now() + " Authorization Request > [" + message.toString() + "]");

                validateTransaction(message);
                saveAuthorization(message);
                if (isApproved == true) {
                    updateAccount(message);
                }
                
                //Respond Message
                authorizationResponseQueue.put(message);
                System.out.println(LocalTime.now() + " Authorization Response > [" + message.toString() + "]");
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private void validateTransaction(InternalMessage message) {
    	isApproved = true;
		message.setTransactionStatus(GOOD);
		account = new Account();
		// Card level validation
    	card = cardDAO.getCard(message.getCardNo());
    	if (card == null) {
            message.setTransactionStatus(CARDNOTFOUND);
            isApproved = false;
    	} else if (card.getStopCode() != 0) {
            message.setTransactionStatus(CARDSTOPPED);
            isApproved = false;
        } else if (card.getCardStatus() != 1 ) {
            message.setTransactionStatus(CARDNOTEMBOSSED);
            isApproved = false;
        } else if (card.getActivationFlag() != 'A') {
            message.setTransactionStatus(CARDNOTACTIVATED);
            isApproved = false;
        } 
    	
    	// Account level validation
    	if (isApproved == true) {
    		account = accountDAO.getAccount(card.getAccountNo());
    		if (account == null) {
    			message.setTransactionStatus(ACCOUNTNOTFOUND);
                isApproved = false;
    		} else if (account.getAgreementStatus() != 9) {
        		message.setTransactionStatus(ACCOUNTINACTIVE);
                isApproved = false;
            } else if ((message.getAmount() + account.getCurrentBalance()) > account.getCreditLimit()) {
        		message.setTransactionStatus(ACCOUNTOVERLIMIT);
                isApproved = false;
            }
    	}
    }
    
    private void saveAuthorization (InternalMessage message) {
        authorization.setCardNo(message.getCardNo());
        authorization.setTransactionDate(message.getTransactionDate());
        authorization.setMerchantID(message.getMerchantId());
        authorization.setTerminalID(message.getTerminalId());
        authorization.setReferenceNo(message.getReferenceNo());
        authorization.setSystemAuditTrace(message.getSystemAuditTrace());
        authorization.setAmount(message.getAmount());
        authorization.setCurrency(message.getCurrency());
        authorization.setTransactionStatus(message.getTransactionStatus());
        
        if (isApproved == true) {
        	AuthResult = "Approved !";
        	Random rand = new Random();
        	int randomNum = rand.nextInt(999999);
            authorization.setApprovalCode(String.format("%06d", randomNum));
            message.setApprovalCode(String.format("%06d", randomNum));
            colorCode = GREEN;  //Green
        } else {
        	AuthResult = "Rejected !";
        	authorization.setApprovalCode(BLANKS);
            message.setApprovalCode(BLANKS);
            colorCode = RED;  //Red
        }
        
        authorizationDAO.insertAuthorization(authorization);
        System.out.println(colorCode + LocalTime.now() + " Authorization " + Thread.currentThread().getName() + 
        		" response " + message.getTransactionStatus() + " " + AuthResult + RESET);    	
    }

    private void updateAccount (InternalMessage message) {
    	accountDAO.updateAccountBalance(account.getAccountNo(), account.getCurrentBalance()+message.getAmount());
    	account = accountDAO.getAccount(account.getAccountNo());
    	System.out.println(LocalTime.now() + " Current Balance: " + BG_YELLOW + account.getCurrentBalance() + RESET);
    	System.out.println(LocalTime.now() + " Open-To-Buy    : " + BG_YELLOW + (account.getCreditLimit()-account.getCurrentBalance()) + RESET);
    }

}