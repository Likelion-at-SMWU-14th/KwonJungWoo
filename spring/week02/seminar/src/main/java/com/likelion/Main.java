package com.likelion;

import com.likelion.bean.Lion;
import com.likelion.bean.Person;
import com.likelion.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);

        System.out.println("Person's name: " + person.getName());
        System.out.println("Person's Lion: " + person.getLion());

        context.close();
    }
}