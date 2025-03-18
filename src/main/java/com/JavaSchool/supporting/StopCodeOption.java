package com.JavaSchool.supporting;

import java.util.ArrayList;
import java.util.List;

public class StopCodeOption {
    private short code;
    private String description;

    public StopCodeOption(short code, String description) {
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

    public static List<StopCodeOption> getOptions() {
        List<StopCodeOption> options = new ArrayList<>();
        options.add(new StopCodeOption((short) 0, "Card Not Stopped"));
        options.add(new StopCodeOption((short) 2, "Card Stopped"));
        return options;
    }
}