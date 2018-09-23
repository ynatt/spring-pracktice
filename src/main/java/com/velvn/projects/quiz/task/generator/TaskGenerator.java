package com.velvn.projects.quiz.task.generator;

import com.velvn.projects.quiz.task.Task;

import java.util.Map;
import java.util.function.Function;

public interface TaskGenerator {

    String[] taskParameters();

    void initGenerator();

    Task nextTask();

    void setParameter(String param, String value);

    Function<String,Boolean> getValidator(String param);
}
