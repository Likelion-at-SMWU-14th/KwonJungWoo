package com.likelion.config;

import com.likelion.bean.Lion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.likelion")
public class ProjectConfig {

    @Bean
    public Lion lion() {
        Lion lion = new Lion();
        lion.setName("jungwoo");
        return lion;
    }

}
