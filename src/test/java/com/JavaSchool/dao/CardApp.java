package com.JavaSchool.dao;

import com.JavaSchool.model.Card;
import com.zaxxer.hikari.HikariDataSource;



public class CardApp {
    public static void main(String[] args) {
        HikariDataSource dataSource = null;
        try {
            // Configure PostgreSQL connection
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/JavaSchoolProject");
            dataSource.setUsername("postgres");  // Change to your credentials
            dataSource.setPassword("123456");

            CardDAO dao = new CardDAO(dataSource);

            Card card = dao.getCard(4391370004285033L);
            if (card != null) {
            	System.out.println("Card: " + card.getCardNo());
            	System.out.println("\n-------- Card Details --------");
            	System.out.println("Card No: " + card.getCardNo());
            	System.out.println("Account No: " + card.getAccountNo());
            	System.out.println("Stop Code: " + card.getStopCode());
            	System.out.println("Card Status: " + card.getCardStatus());
            	System.out.println("Expiry Date: " + card.getExpiryDate());
            	System.out.println("Activation Flag: " + card.getActivationFlag());
            	System.out.println("Activation Date: " + card.getActivationDate());
            	System.out.println("DateTime: " + card.getDateTime());
            	System.out.println("--------------------------------\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dataSource.close();
        }
    }
}