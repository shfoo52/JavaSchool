package com.JavaSchool.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.JavaSchool.model.Account;

@Repository
public class AccountDAO {
    private final DataSource dataSource;

    public AccountDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public void insertAccount(Account account) {
        String sql = "INSERT INTO account (accountno, creditlimit, agreementstatus, openingdate, currentbalance, datetime) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, account.getAccountNo());
            stmt.setInt(2, account.getCreditLimit());
            stmt.setShort(3, account.getAgreementStatus());
            stmt.setDate(4, account.getOpeningDate());
            stmt.setDouble(5, account.getCurrentBalance());
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
            System.out.println("Account inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public Account getAccount(long accountNo) {
        String sql = "SELECT * FROM account WHERE accountno = ?";
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accountNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                    rs.getLong("accountno"),
                    rs.getInt("creditlimit"),
                    rs.getShort("agreementstatus"),
                    rs.getDate("openingdate"),
                    rs.getDouble("currentbalance"),
                    rs.getTimestamp("datetime")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public void updateAccountBalance(long accountNo, double newBalance) {
        String sql = "UPDATE account SET currentbalance = ?, datetime = ? WHERE accountno = ?";
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setLong(3, accountNo);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Account balance updated successfully!");
            } else {
                System.out.println("No account found with account number: " + accountNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}