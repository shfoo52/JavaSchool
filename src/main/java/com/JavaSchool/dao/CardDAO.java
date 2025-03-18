package com.JavaSchool.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.JavaSchool.model.Card;

@Repository
public class CardDAO {
    private final DataSource dataSource;

    public CardDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Transactional
    public void insertCard(Card card) {
        String sql = "INSERT INTO card (cardno, accountno, stopcode, cardstatus, expirydate, activationflag, activationdate, datetime) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, card.getCardNo());
            stmt.setLong(2, card.getAccountNo());
            stmt.setShort(3, card.getStopCode());
            stmt.setShort(4, card.getCardStatus());
            stmt.setInt(5, card.getExpiryDate());
            stmt.setString(6, String.valueOf(card.getActivationFlag()));
            stmt.setDate(7, card.getActivationDate());
            stmt.setTimestamp(8, card.getDateTime());

            stmt.executeUpdate();
            System.out.println("Card inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Transactional // Spring handles the connection & transaction
    public Card getCard(long cardNo) {
        String sql = "SELECT * FROM card WHERE cardno = ? LIMIT 1";  // Ensuring only one record is fetched
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cardNo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Card(
                    rs.getLong("cardno"),
                    rs.getLong("accountno"),
                    rs.getShort("stopcode"),
                    rs.getShort("cardstatus"),
                    rs.getInt("expirydate"),
                    rs.getString("activationflag").charAt(0),
                    rs.getDate("activationdate"),
                    rs.getTimestamp("datetime")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
    }

    @Transactional
    public void updateStopCode(long cardNo, short newStopCode) {
        String sql = "UPDATE card SET stopcode = ?, datetime = ? WHERE cardno = ?";
        try (Connection conn = dataSource.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setShort(1, newStopCode);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setLong(3, cardNo);
            stmt.executeUpdate();
            System.out.println("Card status updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}