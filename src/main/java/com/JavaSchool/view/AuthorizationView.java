package com.JavaSchool.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("app/authorization") // Now accessible at /app/authorization
public class AuthorizationView extends VerticalLayout {
	
	private static final long serialVersionUID = -4122692883329619685L;

	public AuthorizationView() {
        add("Authorization");
    }
}