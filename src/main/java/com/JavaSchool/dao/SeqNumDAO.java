package com.JavaSchool.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.JavaSchool.model.SeqNum;

@Repository
public class SeqNumDAO {
    private final DataSource dataSource;

    public SeqNumDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Insert a new SeqNum record
    public void insertSeqNum(SeqNum seqNum) {
        String sql = "INSERT INTO seqnumber (keytype, seqno, seqmaxlength) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, seqNum.getKeyType());
            stmt.setInt(2, seqNum.getSeqNo());
            stmt.setInt(3, seqNum.getSeqMaxLength());

            stmt.executeUpdate();
            System.out.println("SeqNum record inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read a SeqNum record by keyType
    public SeqNum getSeqNum(String keyType) {
        String sql = "SELECT * FROM seqnumber WHERE keytype = ?";
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, keyType);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new SeqNum(
                        rs.getString("keytype"),
                        rs.getInt("seqno"),
                        rs.getInt("seqmaxlength")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public void updateSeqNo(String keyType, int newSeqNo) {
        String sql = "UPDATE seqnumber SET seqno = ? WHERE keytype = ?";
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newSeqNo);
            stmt.setString(2, keyType);
            stmt.executeUpdate();
            System.out.println("SeqNum updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Method to automatically increment seqNo by 1
    public synchronized int incrementSeqNo(String keyType) {
        String selectSQL = "SELECT seqno, seqmaxlength FROM seqnumber WHERE keytype = ? FOR UPDATE";
        String updateSQL = "UPDATE seqnumber SET seqno = ? WHERE keytype = ?";
        int newSeqNo = -1; // Default value if update fails
        
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
             PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {

            // Start transaction
            conn.setAutoCommit(false);

            // Fetch current seqNo and seqMaxLength with row-level locking
            selectStmt.setString(1, keyType);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int currentSeqNo = rs.getInt("seqno");
                int seqMaxLength = rs.getInt("seqmaxlength");
                int maxSeqNo = (int) (Math.pow(10, seqMaxLength)) - 1;  // Apply the formula

                // Determine new seqNo
                newSeqNo = (currentSeqNo >= maxSeqNo) ? 1 : currentSeqNo + 1;

                // Update seqNo
                updateStmt.setInt(1, newSeqNo);
                updateStmt.setString(2, keyType);
                updateStmt.executeUpdate();

                // Commit transaction
                conn.commit();
                conn.setAutoCommit(true); // Restore default auto-commit behavior
                System.out.println("SeqNo updated for keyType: " + keyType + " -> New SeqNo: " + newSeqNo);
            } else {
                System.out.println("No record found for keyType: " + keyType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return newSeqNo;  // Return new seqNo (or -1 if failed)
    }
}