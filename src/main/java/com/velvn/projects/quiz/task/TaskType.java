package com.velvn.projects.quiz.task;

import com.sun.istack.internal.NotNull;
import com.velvn.projects.quiz.task.generator.TaskGenerator;
import com.velvn.projects.quiz.task.score.ScoreStrategy;

public class TaskType {

    @NotNull
    private final String title;

    @NotNull
    private final String description;

    @NotNull
    private TaskGenerator taskGenerator;

    @NotNull
    private ScoreStrategy scoreStrategy;

    public TaskType(String title, String description, TaskGenerator taskGenerator, ScoreStrategy scoreStrategy) {
        this.title = title;
        this.description = description;
        this.taskGenerator = taskGenerator;
        this.scoreStrategy = scoreStrategy;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskGenerator getTaskGenerator() {
        return taskGenerator;
    }

    public ScoreStrategy getScoreStrategy() {
        return scoreStrategy;
    }

    public void setTaskGenerator(TaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
    }

    public void setScoreStrategy(ScoreStrategy scoreStrategy) {
        this.scoreStrategy = scoreStrategy;
    }
}
