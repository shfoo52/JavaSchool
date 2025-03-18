package com.JavaSchool.view;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.JavaSchool.jpamodel.Card;
import com.JavaSchool.service.CardService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
@Route("app/cards")
@PageTitle("Card Management")
public class CardView extends VerticalLayout implements Serializable {

    private static final long serialVersionUID = 1L;
    private final CardService cardService;
    private final Grid<Card> grid = new Grid<>(Card.class);
    private final TextField searchField = new TextField("Search by Card No");
    private final Button searchButton = new Button("Search");
    private final Button prevButton = new Button("Previous");
    private final Button nextButton = new Button("Next");

    private int currentPage = 0;
    private static final int PAGE_SIZE = 10;
    private List<Card> allCards;
    private boolean isMaintenanceMode = false;

    private final CardDetailInqView cardDetailInqView;
    private final CardDetailMntView cardDetailMntView;

    private final Tab cardInqTab = new Tab("Card Inquiry");
    private final Tab cardMntTab = new Tab("Card Maintenance");
    private final Tabs menuTabs = new Tabs(cardInqTab, cardMntTab);
    private final HorizontalLayout searchLayout = new HorizontalLayout();
    private final HorizontalLayout paginationLayout = new HorizontalLayout(prevButton, nextButton);

    @Autowired
    public CardView(CardService cardService, CardDetailInqView cardDetailInqView, CardDetailMntView cardDetailMntView) {
        this.cardService = cardService;
        this.cardDetailInqView = cardDetailInqView;
        this.cardDetailMntView = cardDetailMntView;

        allCards = cardService.getAllCards(); // Load all cards initially

        searchField.setPlaceholder("Enter 16 digits Card No");
        searchField.setAllowedCharPattern("[0-9]"); // Prevents non-numeric input while typing
        searchField.setMaxLength(16); // Restricts input to exactly 16 digits

        configureGrid();
        configurePagination();

        searchLayout.setSpacing(true);
        searchLayout.setAlignItems(Alignment.BASELINE);
        searchLayout.add(searchField, searchButton);

        searchButton.addClickListener(e -> searchCards());
        prevButton.addClickListener(e -> previousPage());
        nextButton.addClickListener(e -> nextPage());

        menuTabs.addSelectedChangeListener(event -> switchView());

        add(menuTabs, searchLayout, grid, paginationLayout);
        loadGridData();
    }

    private void switchView() {
        isMaintenanceMode = menuTabs.getSelectedTab() == cardMntTab;
        configureGrid(); // Ensure grid item click behavior updates based on mode
    }

    private void configureGrid() {
        grid.setColumns("cardNo", "accountNo", "stopCode", "cardStatus", "expiryDate", "activationFlag", "activationDate", "dateTime");
        grid.getColumnByKey("cardNo").setHeader("Card No").setResizable(true).setWidth("200px").setFlexGrow(0);
        grid.getColumnByKey("accountNo").setHeader("Account No").setResizable(true).setWidth("150px").setFlexGrow(0);
        grid.getColumnByKey("stopCode").setHeader("Stop Code").setResizable(true).setTextAlign(ColumnTextAlign.CENTER).setWidth("120px").setFlexGrow(0);
        grid.getColumnByKey("cardStatus").setHeader("Card Status").setResizable(true).setTextAlign(ColumnTextAlign.CENTER).setWidth("130px").setFlexGrow(0);
        grid.getColumnByKey("expiryDate").setHeader("Expiry Date").setResizable(true).setTextAlign(ColumnTextAlign.CENTER).setWidth("120px").setFlexGrow(0);
        grid.getColumnByKey("activationFlag").setHeader("Activation Flag").setResizable(true).setTextAlign(ColumnTextAlign.CENTER).setWidth("150px").setFlexGrow(0);
        grid.getColumnByKey("activationDate").setHeader("Activation Date").setResizable(true).setTextAlign(ColumnTextAlign.CENTER).setWidth("150px").setFlexGrow(0);
        grid.getColumnByKey("dateTime").setHeader("Date/Time").setResizable(true).setTextAlign(ColumnTextAlign.CENTER).setWidth("210px").setFlexGrow(0);

        grid.addItemClickListener(event -> {
            Card selectedCard = event.getItem();
            if (isMaintenanceMode) {
                cardDetailMntView.setCardDetails(selectedCard);
                cardDetailMntView.open();
            } else {
                cardDetailInqView.setCardDetails(selectedCard);
                cardDetailInqView.open();
            }
        });
    }

    private void configurePagination() {
        prevButton.setEnabled(false);
        nextButton.setEnabled(allCards.size() > PAGE_SIZE);
    }

    private void loadGridData() {
        int start = currentPage * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, allCards.size());
        grid.setItems(allCards.subList(start, end));

        prevButton.setEnabled(currentPage > 0);
        nextButton.setEnabled(end < allCards.size());
    }

    public void refreshGrid() {
        allCards = cardService.getAllCards(); // Reload updated card list
        loadGridData(); // Refresh grid with new data
    }
    
    private void searchCards() {
        String cardNo = searchField.getValue().trim();
        if (cardNo.isEmpty()) {
            allCards = cardService.getAllCards();
        } else {
            allCards = cardService.searchCards(cardNo);
            if (allCards.isEmpty()) {
                Notification.show("Record not found", 3000, Notification.Position.MIDDLE);
            }
        }
        currentPage = 0;
        loadGridData();
    }

    private void nextPage() {
        if ((currentPage + 1) * PAGE_SIZE < allCards.size()) {
            currentPage++;
            loadGridData();
        }
    }

    private void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            loadGridData();
        }
    }
}