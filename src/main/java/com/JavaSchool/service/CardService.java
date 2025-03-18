package com.JavaSchool.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.JavaSchool.DTO.CardResponse;
import com.JavaSchool.jpamodel.Card;
import com.JavaSchool.repository.CardRepository;

@Service
public class CardService {
    
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
    
    public List<Card> searchCards(String cardNo) {
        try {
        	Long cardNoLong = Long.parseLong(cardNo);
        	return cardRepository.findByCardNo(cardNoLong);
        } catch (NumberFormatException e) {
        	return List.of();  // return empty list if conversion fails
        }
    }
    
    // ✅ New API-friendly method
    public CardResponse searchCardAPI(String cardNo) {
        try {
            Long cardNoLong = Long.parseLong(cardNo);
            List<Card> cards = cardRepository.findByCardNo(cardNoLong);
            
            if (!cards.isEmpty()) {
                return new CardResponse(cards.get(0), "AA"); // "AA" = Record found
            } else {
                return new CardResponse(cardNoLong, "AB"); // ✅ Not found, return cardNo & responseCode only
            }
        } catch (NumberFormatException e) {
        	return new CardResponse(0L, "AB");  // 0L (zero) as placeholder for invalid input
        }
    }
    
    public List<Card> getAllCardsAPI() {
    	return cardRepository.findTop10Cards(PageRequest.of(0, 10));
    }    
    
    public boolean updateCard(Card updatedCard) {
        Optional<Card> existingCardOpt = cardRepository.findById(updatedCard.getCardNo());
        if (existingCardOpt.isPresent()) {
            Card existingCard = existingCardOpt.get();
            
            // Update fields
            existingCard.setStopCode(updatedCard.getStopCode());
            existingCard.setCardStatus(updatedCard.getCardStatus());
            existingCard.setExpiryDate(updatedCard.getExpiryDate());
            existingCard.setActivationFlag(updatedCard.getActivationFlag());
            existingCard.setActivationDate(updatedCard.getActivationDate());
            existingCard.setDateTime(new Timestamp(System.currentTimeMillis()));

            cardRepository.save(existingCard);
            return true; // Successfully updated
        }
        return false; // Card not found
    }
}