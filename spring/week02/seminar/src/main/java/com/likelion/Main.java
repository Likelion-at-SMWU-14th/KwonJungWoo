package com.likelion;

import com.likelion.bean.Lion;
import com.likelion.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Lion lion = context.getBean(Lion.class);

        System.out.println(lion.getName());
    }
}