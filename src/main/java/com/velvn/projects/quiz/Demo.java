package com.velvn.projects.quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("quiz.xml");
        Quiz quiz = context.getBean(Quiz.class);
        quiz.start();
    }
}
