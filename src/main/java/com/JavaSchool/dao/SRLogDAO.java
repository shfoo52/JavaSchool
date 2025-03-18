package com.JavaSchool.dao;

import com.JavaSchool.model.InternalMessage;
import com.JavaSchool.model.SRLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SRLogDAO {

    private final DataSource dataSource;

    @Autowired
    public SRLogDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Insert a new SRLog record
    public void insertSRLog(SRLog srlog) {
        String sql = "INSERT INTO srlog (referenceno, source, systemaudittrace, transactionstatus, datetime, message) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setString(1, srlog.getReferenceNo());
            stmt.setString(2, srlog.getSource());
            stmt.setInt(3, srlog.getSystemAuditTrace());
            stmt.setString(4, srlog.getTransactionStatus());
            stmt.setTimestamp(5, srlog.getDateTime());
            stmt.setString(6, srlog.getMessage());
               
            stmt.executeUpdate();
            System.out.println("SRLog record inserted successfully!");
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    // Read SRLog details by Reference No
    public List<SRLog> getSRLogs() {
        List<SRLog> srLogs = new ArrayList<>();
        String sql = "SELECT * FROM srlog ORDER BY referenceno DESC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	SRLog srLog = new SRLog(
            		rs.getString("referenceno"),
            		rs.getString("source"),
            		rs.getInt("systemaudittrace"),
            		rs.getString("transactionstatus"),
            		rs.getTimestamp("datetime"),
            		rs.getString("message"),
            		rs.getTimestamp("datetimeout"),
            		rs.getString("messageout")
            	);
            	srLogs.add(srLog);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return srLogs;
    }

    // Update SRLog transaction status
    public void updateSRLogResponse(String referenceNo, String newStatus, InternalMessage messageResponse) {
        String sql = "UPDATE srlog SET transactionstatus = ?, datetimeout = ?, messageout = ? WHERE referenceno = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setString(3, messageResponse.toString());
            stmt.setString(4, referenceNo);

            stmt.executeUpdate();
            System.out.println("SRLog transaction status updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}