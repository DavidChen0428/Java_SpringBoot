package com.david.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HttpListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpListenerApplication.class, args);
    }

}
