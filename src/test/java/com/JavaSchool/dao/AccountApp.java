package com.JavaSchool.dao;

import com.JavaSchool.model.Account;
import com.zaxxer.hikari.HikariDataSource;

public class AccountApp {
    public static void main(String[] args) {
        HikariDataSource dataSource = null;
        try {
            // 1. Configure PostgreSQL connection
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/JavaSchoolProject");
            dataSource.setUsername("postgres");  // Change to your credentials
            dataSource.setPassword("123456");

            // 2. Create table (one-time setup)
            //try (var conn = dataSource.getConnection();
            //     var stmt = conn.createStatement()) {
            //    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS account (" +
            //        "accountno BIGINT PRIMARY KEY, " +
            //        "creditlimit INTEGER, " +
            //        "agreementstatus SMALLINT, " +
            //        "openingdate DATE, " +
            //        "currentbalance DOUBLE PRECISION, " +
            //        "datetime TIMESTAMP)");
            //}

            AccountDAO dao = new AccountDAO(dataSource);

            // 3. Test 1: Insert and retrieve account
            //System.out.println("--- Testing Existing Account ---");
            //Account testAccount = new Account(
            //    123L,
            //    5000,
            //    (short) 1,
            //    Date.valueOf(LocalDate.now()),
            //    1000.0,
            //    null
            //);
            //dao.insertAccount(testAccount);
            
            Account retrieved = dao.getAccount(100001L);
            if (retrieved != null) {
                System.out.println("Fetched Account: " + retrieved.getAccountNo());
                System.out.println("\n-------- Account Details --------");
                System.out.println("Account Number  : " + retrieved.getAccountNo());
                System.out.println("Credit Limit    : " + retrieved.getCreditLimit());
                System.out.println("Agreement Status: " + retrieved.getAgreementStatus());
                System.out.println("Opening Date    : " + retrieved.getOpeningDate());
                System.out.println("Current Balance : " + retrieved.getCurrentBalance());
                System.out.println("DateTime        : " + retrieved.getDateTime());
                System.out.println("----------------------------------\n");
                System.out.println("Balance: " + retrieved.getCurrentBalance());
            } else {
                System.out.println("FAILED: Account not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. Cleanup
           // if (dataSource != null) {
           //     try (var conn = dataSource.getConnection();
           //          var stmt = conn.createStatement()) {
           //         stmt.executeUpdate("DROP TABLE IF EXISTS account");
           //     } catch (Exception e) {
            //        e.printStackTrace();
            //    }
                dataSource.close();
            //}
        }
    }
}