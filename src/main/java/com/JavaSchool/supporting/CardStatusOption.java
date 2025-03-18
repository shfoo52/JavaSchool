package com.JavaSchool.supporting;

import java.util.ArrayList;
import java.util.List;

public class CardStatusOption {
    private short code;
    private String description;

    public CardStatusOption(short code, String description) {
        this.code = code;
        this.description = description;
    }

    public short getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code + " - " + description; // How it will appear in the dropdown
    }

    public static List<CardStatusOption> getOptions() {
        List<CardStatusOption> options = new ArrayList<>();
        options.add(new CardStatusOption((short) 0, "Card Not Embossed"));
        options.add(new CardStatusOption((short) 1, "Card Embossed"));
        return options;
    }
}