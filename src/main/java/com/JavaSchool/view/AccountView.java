package com.JavaSchool.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("app/accounts") // Now accessible at /app/accounts
public class AccountView extends VerticalLayout {

	private static final long serialVersionUID = 2912760662164504033L;

	public AccountView() {
        add("Account Management");
    }
}