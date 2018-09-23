package com.velvn.projects.quiz.task.score;

public class SimpleScoreStrategy implements ScoreStrategy {

    private final int maxAttempts;

    public SimpleScoreStrategy(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public int maxAttempts() {
        return maxAttempts;
    }

    @Override
    public double score(int successes, int fails, int attempt, double baseSuccess, double baseFail, double baseAttempt,                         boolean isRight, boolean skip) {
        if(!isRight){
            if(!skip) {
                return attempt * baseAttempt;
            }else {
                return baseFail;
            }
        }else {
            return baseSuccess;
        }
    }
}
