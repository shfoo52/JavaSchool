package com.JavaSchool.dao;

import com.zaxxer.hikari.HikariDataSource;

//import java.sql.Timestamp;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import com.JavaSchool.model.Authorization;

public class AuthorizationApp {
	public static void main(String[] args) {
        HikariDataSource dataSource = null;
        try {
            // Configure PostgreSQL connection
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/JavaSchoolProject");
            dataSource.setUsername("postgres");  // Change to your credentials
            dataSource.setPassword("123456");
            
            AuthorizationDAO dao = new AuthorizationDAO(dataSource);

            List<Authorization> authorizations = dao.getAllAuthorizations();

            System.out.println("Total authorizations records: " + authorizations.size());
            System.out.println("\nAuthorization List (should be ordered newest first):");
            
            System.out.println("========================================================================================="
            		+ "=====================================================================");
            System.out.printf("| %-16s | %-11s | %-10s | %-8s | %-13s | %-13s | %-11s | %12s | %-5s | %-18s | %-7s |%n",
                "Card No", "Trans Date", "Merchant", "Terminal", "Approval Code", "Reference No", 
                "Audit Trace", "Amount", "Curr", "Datetime", "Status");
            System.out.println("========================================================================================="
            		+ "=====================================================================");

            authorizations.forEach(auth -> {
                System.out.printf("| %-16d | %-11s | %-10d | %-8s | %-13s | %-13s | %-11d | %12.2f | %-5s | %-18s | %-7s |%n",
                    auth.getCardNo(),
                    auth.getTransactionDate(),
                    auth.getMerchantID(),
                    auth.getTerminalID(),
                    auth.getApprovalCode(),
                    auth.getReferenceNo(),
                    auth.getSystemAuditTrace(),
                    auth.getAmount(),
                    auth.getCurrency(),
                    auth.getDateTime().toString().substring(0, 16), // Truncate milliseconds
                    auth.getTransactionStatus());
            });

            System.out.println("========================================================================================="
            		+ "=====================================================================");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            	dataSource.close();
            }
        }
                    
	    // Insert a sample record
	    //Timestamp now = new Timestamp(System.currentTimeMillis());
	    //Authorization auth = new Authorization(1234567890123456L, now, 12345, "T1234567", "A12345", 
	    //                                       "R12345678901", 1001, 250.75, "USD", now);
	    //dao.insertAuthorization(auth);

	    // Create a map of fields to update
	    //Map<String, Object> updates = new HashMap<>();
	    //updates.put("Amount", 350.00);
	    //updates.put("Currency", "EUR");
	    //updates.put("Merchant_ID", 67890);
	    //updates.put("Approval_Code", "B67890");

	    // Call update method with multiple fields
	    //dao.updateAuthorization(1234567890123456L, updates);

	    //DatabaseConnection.closeConnection();  // Close DB connection
	    //System.out.println("Multi-field update completed.");
}
