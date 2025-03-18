package com.JavaSchool.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("app/parameter") // Now accessible at /app/parameters
public class ParameterView extends VerticalLayout {

	private static final long serialVersionUID = -8683723596565447825L;

	public ParameterView() {
        add("Parameters Management");
    }
}