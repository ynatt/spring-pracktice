package com.velvn.projects.quiz.communication;

public interface Communicator {

    String askQuestion(String message);

    String askParameter(String parameter);

    void showMessage(String message);

}
