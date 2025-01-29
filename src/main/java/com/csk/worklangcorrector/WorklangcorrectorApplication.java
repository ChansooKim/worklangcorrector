package com.csk.worklangcorrector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.csk.worklangcorrector.repository")
@EntityScan(basePackages = "com.csk.worklangcorrector.model") // 엔티티 위치
public class WorklangcorrectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorklangcorrectorApplication.class, args);
    }

}
