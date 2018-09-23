package com.velvn.projects.quiz.task;

import java.util.Map;
import java.util.TreeMap;

public class OptionedTask extends Task {

    private Map<String,String> options = new TreeMap<>();

    public OptionedTask(String question, String answer, Map<String, String> options) {
        super(question, answer);
        this.options = options;
    }

    public Map<String, String> getOptions() {
        return options;
    }
}
