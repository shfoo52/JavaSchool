package com.JavaSchool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaSchool.DTO.CardRequest;
import com.JavaSchool.DTO.CardResponse;
import com.JavaSchool.jpamodel.Card;
import com.JavaSchool.service.CardService;

@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }
   
    @PostMapping("/getcarddetail")
    public CardResponse getCardDetail(@RequestBody CardRequest request) {
        return cardService.searchCardAPI(request.getCardNo());
    }    
    
    @GetMapping("/getallcards")
    public CardResponse getAllCards() {
        List<Card> cards = cardService.getAllCardsAPI();
        return new CardResponse(cards, "AA"); // Return multiple cards with response code "AA"        
    }
    
    //public List<CardResponse> getAllCards() {
    //    List<Card> cards = cardService.getTop10Cards();        
    //return cards.stream()  // Return response code & cards multiple times
    //        .map(card -> new CardResponse(card, "AA")) // "AA" for successful response
    //        .toList();
    //}
}