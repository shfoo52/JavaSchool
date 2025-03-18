package com.JavaSchool.view;

import org.springframework.context.ApplicationContext;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Component;

@Route("")
public class MainView extends SplitLayout {
    
	private static final long serialVersionUID = 7383472782699554493L;
	private final VerticalLayout menuLayout = new VerticalLayout();
    private final Div contentLayout = new Div();
    private final ApplicationContext context; // Inject Spring context

    // private com.vaadin.flow.component.Component VaadinComponent;

    public MainView(ApplicationContext context) {
        this.context = context;
		createMenu();
        setSizeFull();
        setOrientation(Orientation.HORIZONTAL);
        setSplitterPosition(15);
        
        addToPrimary(menuLayout);
        addToSecondary(contentLayout);
        
        menuLayout.setWidth("100%");
        menuLayout.getStyle().set("background-color", "#f0f0f0"); // Light grey background
        menuLayout.getStyle().set("min-width", "200px"); // Set minimum width for the menu
        contentLayout.setSizeFull();
    }
    
    private void createMenu() {
        Button cardButton = new Button("Card", e -> loadContent(CardView.class));
        Button accountButton = new Button("Account", e -> loadContent(AccountView.class));
        Button authButton = new Button("Authorization", e -> loadContent(AuthorizationView.class));
        Button logButton = new Button("Subrouter Log", e -> loadContent(SubrouterView.class));
        Button paramButton = new Button("Parameters", e -> loadContent(ParameterView.class));
        
        menuLayout.add(cardButton, accountButton, authButton, logButton, paramButton);
        menuLayout.setSpacing(true);
        menuLayout.setPadding(true);
    }
    
    private void loadContent(Class<? extends Component> viewClass) {
        contentLayout.removeAll(); // Clear previous content
        Component view = context.getBean(viewClass); // Get the view from Spring
        contentLayout.add(view); // Load new content inside right panel
    }
}
