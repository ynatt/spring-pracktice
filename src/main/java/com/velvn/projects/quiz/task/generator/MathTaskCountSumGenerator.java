package com.velvn.projects.quiz.task.generator;

import com.velvn.projects.quiz.task.Task;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class MathTaskCountSumGenerator implements TaskGenerator {

    private static final String[] parameters = {"min number value", "max number value"};

    private final Map<String,Function<String,Boolean>> parametersOnValidators = new HashMap<>();

    {
        parametersOnValidators.put(parameters[0], s -> s.matches("(-)?[0-9]+"));
        parametersOnValidators.put(parameters[1], s -> s.matches("(-)?[0-9]+"));
    }

    private final Map<String,Integer> paramsInMap = new HashMap<>();

    {
        paramsInMap.put(parameters[0],1);
        paramsInMap.put(parameters[1],10);
    }

    private Random random;

    @Override
    public String[] taskParameters() {
        return parameters;
    }

    @Override
    public void initGenerator() {
        System.out.println(min() + " " + max());
        random = new Random();
    }

    public Function<String,Boolean> getValidator(String param){
        return parametersOnValidators.get(param);
    }

    @Override
    public Task nextTask() {
        int a = generateNumber();
        int b = generateNumber();
        String question = a + " + " + b;
        String answer = "" + (a + b);
        return new Task(question,answer);
    }

    @Override
    public void setParameter(String param, String value){
        if (paramsInMap.containsKey(param)) {
            paramsInMap.put(param, Integer.valueOf(value));
        }
    }

    private int min(){
        return paramsInMap.get(parameters[0]);
    }

    private int max(){
        return paramsInMap.get(parameters[1]);
    }

    private int generateNumber(){
        return random.nextInt(max() - min() + 1) + min();
    }
}
