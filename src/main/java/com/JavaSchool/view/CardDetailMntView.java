package com.JavaSchool.view;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.JavaSchool.jpamodel.Card;
import com.JavaSchool.service.CardService;
import com.JavaSchool.supporting.ActivationFlagOption;
import com.JavaSchool.supporting.CardStatusOption;
import com.JavaSchool.supporting.StopCodeOption;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
class CardDetailMntView extends CardDetailView {

	private static final long serialVersionUID = -9013634701955643810L;
    private final Button saveButton = new Button("Save");
    private final CardService cardService;
    private final ApplicationContext context; // 🔹 Get ApplicationContext instead of injecting CardView directly
    private Card currentCard;

    // ✅ ComboBox with descriptions
    private final ComboBox<StopCodeOption> stopCode = new ComboBox<>("Stop Code");
    private final ComboBox<CardStatusOption> cardStatus = new ComboBox<>("Card Status");
    private final ComboBox<ActivationFlagOption> activationFlag = new ComboBox<>("Activation Flag");

	@Autowired
	public CardDetailMntView(CardService cardService, ApplicationContext context) {
		super();
	    this.cardService = cardService;
        this.context = context;
        
        configureDropdowns();
        
        saveButton.addClickListener(e -> saveCard());
        add(stopCode, cardStatus, activationFlag, saveButton);
	}

    private void configureDropdowns() {
        stopCode.setItems(StopCodeOption.getOptions());
        stopCode.setItemLabelGenerator(StopCodeOption::toString); // Displays "code - description"
        cardStatus.setItems(CardStatusOption.getOptions());
        cardStatus.setItemLabelGenerator(CardStatusOption::toString); 
        activationFlag.setItems(ActivationFlagOption.getOptions());
        activationFlag.setItemLabelGenerator(ActivationFlagOption::toString); 
    }
    
    @Override
	public void setCardDetails(Card card) {
		this.currentCard = card;
		//setHeaderTitle("Card Detail Maintenance");
        
		// Ensure items are set before calling setValue()
        if (!stopCode.getDataProvider().isInMemory() ||
        	!cardStatus.getDataProvider().isInMemory() ||	
        	!activationFlag.getDataProvider().isInMemory()) {
            configureDropdowns();
        }
		
        cardNo.setValue(String.valueOf(card.getCardNo()));
        accountNo.setValue(String.valueOf(card.getAccountNo()));
        stopCode.setValue(StopCodeOption.getOptions().stream()
                .filter(option -> option.getCode() == card.getStopCode())
                .findFirst()
                .orElse(null));
        cardStatus.setValue(CardStatusOption.getOptions().stream()
                .filter(option -> option.getCode() == card.getCardStatus())
                .findFirst()
                .orElse(null));
        expiryDate.setValue(String.valueOf(card.getExpiryDate()));
        activationFlag.setValue(ActivationFlagOption.getOptions().stream()
                .filter(option -> option.getCode() == card.getActivationFlag())
                .findFirst()
                .orElse(null));
        activationDate.setValue(String.valueOf(card.getActivationDate()));
        
        // Set editable fields for maintenance
        cardNo.setReadOnly(true);
        accountNo.setReadOnly(true);
        stopCode.setReadOnly(false);
        cardStatus.setReadOnly(false);
        expiryDate.setReadOnly(false);
        activationFlag.setReadOnly(false);
        activationDate.setReadOnly(false);
        
        removeAll();
        H3 title = new H3("Card Detail Maintenance");
        // ✅ Make sure form contains dropdowns
        add(title, createFormLayout(), new HorizontalLayout(backButton, saveButton));
        open();
	}
	
    @Override
    protected FormLayout createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.setSizeUndefined(); 
        formLayout.setResponsiveSteps(
        		new FormLayout.ResponsiveStep("0", 1),  
        		new FormLayout.ResponsiveStep("500px", 2)
        );
        // Replace text fields with dropdown
        formLayout.add(cardNo, accountNo, stopCode, cardStatus, expiryDate, activationFlag, activationDate);
        return formLayout;
    }
    
    private void saveCard() {
        if (currentCard != null) {
            StopCodeOption selectedStopCode = stopCode.getValue();
            if (selectedStopCode != null) {
                currentCard.setStopCode(selectedStopCode.getCode()); // Save only the short code
            }
            CardStatusOption selectedCardStatus = cardStatus.getValue();
            if (selectedCardStatus != null) {
                currentCard.setCardStatus(selectedCardStatus.getCode()); 
            }
            currentCard.setExpiryDate(Integer.parseInt(expiryDate.getValue()));
            ActivationFlagOption selectedActivationFlag = activationFlag.getValue();
            if (activationFlag != null) {
                currentCard.setActivationFlag(selectedActivationFlag.getCode());
            }            
            Date activationDateValue = Date.valueOf(activationDate.getValue()); // Converts "YYYY-MM-DD" string to SQL Date
            currentCard.setActivationDate(activationDateValue); // Directly sets SQL Date            
            
            boolean updated = cardService.updateCard(currentCard);
            if (updated) {
                Notification.show("Card updated successfully!", 3000, Notification.Position.MIDDLE);
                CardView cardView = context.getBean(CardView.class);
                cardView.refreshGrid();  // 🔹 Call refreshGrid() after saving
                close();
            } else {
                Notification.show("Update failed! Card not found.", 3000, Notification.Position.MIDDLE);
            }
        }
    }
}
