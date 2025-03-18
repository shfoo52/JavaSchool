package com.JavaSchool.dao;

import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

import com.JavaSchool.model.SRLog;

public class SRLogApp {
    public static void main(String[] args) {
        HikariDataSource dataSource = null;
        try {
            // Configure PostgreSQL connection
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/JavaSchoolProject");
            dataSource.setUsername("postgres");  // Change to your credentials
            dataSource.setPassword("123456");
            
            SRLogDAO dao = new SRLogDAO(dataSource);

            List<SRLog> fetchedSRLogs = dao.getSRLogs();
            if (fetchedSRLogs.isEmpty()) {
            	System.out.println("No SRLog records found.");
            } else {
                System.out.println("Total Subrouter Log records: " + fetchedSRLogs.size());
                System.out.println("=========================================================================================");
                System.out.printf("| %-14s | %-7s | %-12s | %-18s | %-21s |%n",
                        "Reference No", "Source", "Audit Trace", "Transaction Status", "DateTime");
                System.out.println("=========================================================================================");

            	
            	fetchedSRLogs.forEach(srLog -> {
                    System.out.printf("| %-14s | %-7s | %-12s | %-18s | %-20s |%n",
                    		srLog.getReferenceNo(),
                    		srLog.getSource(),
                    		srLog.getSystemAuditTrace(),
                    		srLog.getTransactionStatus(),
                    		srLog.getDateTime());
            	});
                System.out.println("=========================================================================================");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataSource.close();
        }
    }
}