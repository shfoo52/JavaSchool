package com.JavaSchool.DTO;

import java.util.List;

import com.JavaSchool.jpamodel.Card;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null fields from JSON response
public class CardResponse {
    private Long cardNo;
    private String responseCode;
    private Card card;
    private List<Card> cards; // For multiple card response

    // Constructor for found card	
    public CardResponse(Card card, String responseCode) {
        this.card = card;
        this.responseCode = responseCode;
        this.cardNo = card.getCardNo();
    }

    // Constructor for NOT found case
    public CardResponse(Long cardNo, String responseCode) {
        this.cardNo = cardNo;
        this.responseCode = responseCode;
        this.card = null;
    }
    
 // Constructor for card list
    public CardResponse(List<Card> cards, String responseCode) {
        this.cards = cards;
        this.responseCode = responseCode;
        this.card = null; // Ensure only one type is used
    }

    /*
     * Serialization to JSON (Spring Boot & Jackson)
     *
     * Spring Boot uses Jackson to automatically convert Java objects to JSON.
     * Jackson relies on getter methods to extract field values when serializing an object.
     * Without getCard(), Jackson will not include the card field in the API response.
     *
     * If getter is missing, the API will not include the those object in its output, 
     * even if it was set in the constructor.
     */
    public Long getCardNo() {
    	return cardNo;
    }

    public String getResponseCode() {
    	return responseCode;
    }
    
    public Card getCard() { 
    	return card; 
    }
    
    public List<Card> getCards() {
        return cards;
    }
}