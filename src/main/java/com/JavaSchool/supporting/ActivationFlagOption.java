package com.JavaSchool.supporting;

import java.util.ArrayList;
import java.util.List;

public class ActivationFlagOption {
    private char code;
    private String description;

    public ActivationFlagOption(char code, String description) {
        this.code = code;
        this.description = description;
    }

    public char getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code + " - " + description; // How it will appear in the dropdown
    }

    public static List<ActivationFlagOption> getOptions() {
        List<ActivationFlagOption> options = new ArrayList<>();
        options.add(new ActivationFlagOption('A', "Activated"));
        options.add(new ActivationFlagOption('D', "Deactivated"));
        return options;
    }
}