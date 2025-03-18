package com.JavaSchool.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.JavaSchool.model.Authorization;

@Repository
public class AuthorizationDAO {
    private final DataSource dataSource;

    public AuthorizationDAO(DataSource dataSource) {
    	this.dataSource = dataSource;
    }

    @Transactional
    public void insertAuthorization(Authorization auth) {
        String sql = "INSERT INTO \"authorization\" (cardno, transactiondate, merchantid, terminalid, approvalcode, referenceno, systemaudittrace, "
        		+ "amount, currency, datetime, transactionstatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, auth.getCardNo());
            stmt.setDate(2, auth.getTransactionDate());
            stmt.setInt(3, auth.getMerchantID());
            stmt.setString(4, auth.getTerminalID());
            stmt.setString(5, auth.getApprovalCode());
            stmt.setString(6, auth.getReferenceNo());
            stmt.setInt(7, auth.getSystemAuditTrace());
            stmt.setDouble(8, auth.getAmount());
            stmt.setString(9, auth.getCurrency());
            stmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
            stmt.setString(11, auth.getTransactionStatus());

            stmt.executeUpdate();
            System.out.println("Authorization record inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Authorization> getAllAuthorizations() {
        List<Authorization> list = new ArrayList<>();
        String sql = "SELECT * FROM \"authorization\" ORDER BY datetime DESC";

        try (Connection conn = dataSource.getConnection();
        	 Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Authorization auth = new Authorization(
                        rs.getLong("cardno"),
                        rs.getDate("transactiondate"),
                        rs.getInt("merchantid"),
                        rs.getString("terminalid"),
                        rs.getString("approvalcode"),
                        rs.getString("referenceno"),
                        rs.getInt("systemaudittrace"),
                        rs.getDouble("amount"),
                        rs.getString("currency"),
                        rs.getTimestamp("datetime"),
                        rs.getString("transactionstatus")
                );
                list.add(auth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    public void updateAuthorization(long cardNumber, Map<String, Object> updates) {
        // List of allowed fields to prevent SQL injection
        List<String> allowedFields = Arrays.asList("transactiondate", "merchantid", "terminalid", "approvalcode", 
                                                   "referenceno", "systemaudittrace", "amount", "currency", "datetime", "transactionstatus");

        // Validate if all given fields are allowed
        for (String field : updates.keySet()) {
            if (!allowedFields.contains(field)) {
                System.out.println("Invalid field name: " + field);
                return;
            }
        }

        // Construct the SQL query dynamically
        StringBuilder sql = new StringBuilder("UPDATE \"authorization\" SET ");
        List<Object> values = new ArrayList<>();
        
        for (String field : updates.keySet()) {
            sql.append(field).append(" = ?, ");
            values.add(updates.get(field));
        }
        sql.delete(sql.length() - 2, sql.length());  // Remove trailing comma
        sql.append(" WHERE Card_Number = ?");

        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            for (Object value : values) {
                if (value instanceof Integer) {
                    stmt.setInt(index, (Integer) value);
                } else if (value instanceof Double) {
                    stmt.setDouble(index, (Double) value);
                } else if (value instanceof Long) {
                    stmt.setLong(index, (Long) value);
                } else if (value instanceof Timestamp) {
                    stmt.setTimestamp(index, (Timestamp) value);
                } else if (value instanceof String) {
                    stmt.setString(index, (String) value);
                } else {
                    System.out.println("Unsupported data type: " + value.getClass().getSimpleName());
                    return;
                }
                index++;
            }

            stmt.setLong(index, cardNumber);  // Set Card_Number in WHERE clause

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Authorization record updated successfully.");
            } else {
                System.out.println("No matching record found for Card Number: " + cardNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}