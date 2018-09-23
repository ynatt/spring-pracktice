package com.velvn.projects.quiz.communication;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleCommunicator implements Communicator {

    private PrintStream out;

    private Scanner in;

    public ConsoleCommunicator(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
    }

    public String askQuestion(String message) {
        out.println(message);
        out.print("Ответ: ");
        return in.nextLine();
    }

    @Override
    public String askParameter(String parameter) {
        out.print("Enter "+ parameter + " = ");
        return in.nextLine();
    }

    @Override
    public void showMessage(String message) {
        out.println(message);
    }
}
