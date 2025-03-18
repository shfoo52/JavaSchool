package com.JavaSchool.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.JavaSchool.jpamodel.Card;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
class CardDetailInqView extends CardDetailView {
	
	private static final long serialVersionUID = 6762411310992705307L;

	@Autowired
    //public CardDetailInqView(CardService cardService) {
    //    super(cardService);
    //}
    public CardDetailInqView() {
        super();
    }

    @Override
    public void setCardDetails(Card card) {
        //setHeaderTitle("Card Detail Inquiry");
        cardNo.setValue(String.valueOf(card.getCardNo()));
        accountNo.setValue(String.valueOf(card.getAccountNo()));
        stopCode.setValue(String.valueOf(card.getStopCode()));
        cardStatus.setValue(String.valueOf(card.getCardStatus()));
        expiryDate.setValue(String.valueOf(card.getExpiryDate()));
        activationFlag.setValue(String.valueOf(card.getActivationFlag()));
        activationDate.setValue(String.valueOf(card.getActivationDate()));
        
        // Set fields as read-only
        cardNo.setReadOnly(true);
        accountNo.setReadOnly(true);
        stopCode.setReadOnly(true);
        cardStatus.setReadOnly(true);
        expiryDate.setReadOnly(true);
        activationFlag.setReadOnly(true);
        activationDate.setReadOnly(true);
        
        removeAll();
        H3 title = new H3("Card Detail Inquiry");
        add(title, createFormLayout(), new HorizontalLayout(backButton));
        open();
    }
}