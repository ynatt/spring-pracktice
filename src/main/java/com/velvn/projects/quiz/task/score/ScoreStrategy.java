package com.velvn.projects.quiz.task.score;

public interface ScoreStrategy {

    int maxAttempts();

    double score(int successes, int fails, int attempt, double baseSuccess,
                 double baseFail, double baseAttempt, boolean isRight, boolean skip);

}
