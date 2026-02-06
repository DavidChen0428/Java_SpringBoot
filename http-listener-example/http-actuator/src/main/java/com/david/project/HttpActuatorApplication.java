package com.david.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpActuatorApplication {

    public static void main(String[] args) {
        System.setProperty("server.port", "9001");
        SpringApplication.run(HttpActuatorApplication.class, args);
    }

}
