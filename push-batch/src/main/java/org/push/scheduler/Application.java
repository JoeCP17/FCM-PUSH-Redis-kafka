package org.push.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.push"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}