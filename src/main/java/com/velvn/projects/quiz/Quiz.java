package com.velvn.projects.quiz;

import com.velvn.projects.quiz.communication.Communicator;
import com.velvn.projects.quiz.task.Task;
import com.velvn.projects.quiz.task.TaskType;
import com.velvn.projects.quiz.task.generator.TaskGenerator;

import java.util.List;


public class Quiz {

    private List<TaskType> taskTypes;

    private static final String exitCommand = "!exit";

    private boolean exit;

    private static final String skipTaskCommand = "!skip";

    private boolean skip;

    private Communicator communicator;

    public Quiz(Communicator communicator, List<TaskType> taskTypes) {
        this.communicator = communicator;
        this.taskTypes = taskTypes;
    }

    public void start() {
        showTaskTypes();
        askTaskType();
    }

    private void askTaskType() {
        TaskType taskType;
        String userInput = commandFilter(communicator.askParameter("task type"));
        while (!exit) {
            taskType = findTaskType(userInput);
            if (taskType == null) {
                userInput = commandFilter(communicator.askParameter("task type"));
                continue;
            }
            startTask(taskType);
        }
    }

    private void startTask(TaskType taskType) {
        askTaskParameters(taskType.getTaskGenerator());
        Task task;
        String answer;
        int successes = 0;
        int fails = 0;
        double score = 0;
        while (!exit) {
            task = taskType.getTaskGenerator().nextTask();
            for (int attempt = 1; attempt <= taskType.getScoreStrategy().maxAttempts(); attempt++) {
                answer = commandFilter(communicator.askQuestion(task.question()));
                if (skip) {
                    fails++;
                    score += taskType.getScoreStrategy().score(successes, fails, attempt, 300, -100, -10, false, true);
                    showScore(score);
                    break;
                }
                if (exit) {
                    break;
                }
                if (answer.equals(task.answer())) {//Right answer
                    successes++;
                    score += taskType.getScoreStrategy().score(successes, fails, attempt, 300, -100, -10, true, false);
                    showScore(score);
                    break;
                } else {
                    if (attempt == taskType.getScoreStrategy().maxAttempts()) {//Wrong answer and all attempts lost
                        communicator.showMessage("Zero attempts left you failed task");
                        fails++;
                        score += taskType.getScoreStrategy()
                                .score(successes, fails, attempt, 300, -100, -10, false, false);
                        showScore(score);
                    } else {////Wrong answer but not all attempts lost
                        communicator.showMessage("Wrong, you have " +
                                (taskType.getScoreStrategy().maxAttempts() - attempt) + " attempts left");
                        score += taskType.getScoreStrategy()
                                .score(successes, fails, attempt, 300, -100, -10, false, false);
                        showScore(score);
                    }
                }
            }
        }
    }

    private void showScore(double score){
        communicator.showMessage("Your score : " + score);
    }

    private void askTaskParameters(TaskGenerator generator) {
        String[] parameters = generator.taskParameters();
        String input;
        for (String parameter : parameters) {
            input = communicator.askParameter(parameter);
            if (generator.getValidator(parameter).apply(input)) {
                generator.setParameter(parameter, input);
            }
        }
        generator.initGenerator();
    }


    private void showTaskTypes() {
        communicator.showMessage("Task types:");
        for (int i = 0, end = taskTypes.size(); i < end; i++) {
            communicator.showMessage((i + 1) + ". " + taskTypes.get(i).getTitle());
        }
    }

    private String commandFilter(String userInput) {
        if (userInput.toLowerCase().matches(exitCommand)) {
            exit = true;
        }
        if (userInput.toLowerCase().matches(skipTaskCommand)) {
            skip = true;
        }
        return userInput;
    }

    private TaskType findTaskType(String userInput) {
        if (userInput.matches("[0-9]+")) {
            int index = Integer.valueOf(userInput) - 1;
            if (index > -1 && index < taskTypes.size()) {
                return taskTypes.get(index);
            } else {
                return null;
            }
        } else {
            for (TaskType taskType : taskTypes) {
                if (taskType.getTitle().toLowerCase().matches(userInput.toLowerCase())) {
                    return taskType;
                }
            }
        }
        return null;
    }
}
