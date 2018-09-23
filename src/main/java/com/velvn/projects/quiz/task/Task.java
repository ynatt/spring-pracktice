package com.velvn.projects.quiz.task;

public class Task {

    private final String question;

    private final String answer;

    public Task(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String question(){
        return question;
    }

    public String answer() {
        return answer;
    }


}
