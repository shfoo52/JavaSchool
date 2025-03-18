package com.JavaSchool.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("app/subrouter") // Now accessible at /app/subrouter-logs
public class SubrouterView extends VerticalLayout {

	private static final long serialVersionUID = 957523763743290694L;

	public SubrouterView() {
        add("Subrouter Logs");
    }
}