package com.JavaSchool.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.JavaSchool.jpamodel.Card;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public abstract class CardDetailView extends Dialog {
	private static final long serialVersionUID = 3080160810907220590L;
	protected final TextField cardNo = new TextField("Card No");
	protected final TextField accountNo = new TextField("Account No");
	protected final TextField stopCode = new TextField("Stop Code");
	protected final TextField cardStatus = new TextField("Card Status");
	protected final TextField expiryDate = new TextField("Expiry Date");
	protected final TextField activationFlag = new TextField("Activation Flag");
	protected final TextField activationDate = new TextField("Activation Date");
	protected final Button backButton = new Button("Back");
	protected final Button saveButton = new Button("Save");

    @Autowired
    public CardDetailView() {
        setWidth("700px");  // Adjust width
        // setHeight("500px"); // Adjust height
        setHeight(null); // Auto adjust height based on content
        setResizable(true); // Allow resizing if needed
        setDraggable(true); // Allow dragging
		setModal(true);
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);
        backButton.addClickListener(e -> close());  // Close the dialog
    }
    
    //protected void setCardDetails(Card card) {
    //    cardNo.setValue(String.valueOf(card.getCardNo()));
    //    accountNo.setValue(String.valueOf(card.getAccountNo()));
    //    stopCode.setValue(String.valueOf(card.getStopCode()));
    //    cardStatus.setValue(String.valueOf(card.getCardStatus()));
    //    expiryDate.setValue(String.valueOf(card.getExpiryDate()));
    //    activationFlag.setValue(String.valueOf(card.getActivationFlag()));
    //    activationDate.setValue(String.valueOf(card.getActivationDate()));
        // Set fields editable or read-only based on mode
    //    cardNo.setReadOnly(true);
    //    accountNo.setReadOnly(true);
    //    stopCode.setReadOnly(true);
    //    cardStatus.setReadOnly(true);
    //    expiryDate.setReadOnly(true);
    //    activationFlag.setReadOnly(true);
    //    activationDate.setReadOnly(true);
        
    //    saveButton.setVisible(false);
    //}
     
    public abstract void setCardDetails(Card card);    
    
    protected FormLayout createFormLayout() {
    	// H3 title = new H3("Card Detail Inquiry");
      
        FormLayout formLayout = new FormLayout();
        formLayout.setSizeUndefined(); // Allow height to adjust automatically
        formLayout.setResponsiveSteps(
        		new FormLayout.ResponsiveStep("0", 1),  // Single column for very small screens
        		new FormLayout.ResponsiveStep("500px", 2) // Two columns when width is ≥ 600px
        );
        formLayout.add(cardNo, accountNo, stopCode, cardStatus, expiryDate, activationFlag, activationDate);
        return formLayout;
    }
}