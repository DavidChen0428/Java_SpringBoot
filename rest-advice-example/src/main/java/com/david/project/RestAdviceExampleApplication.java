package com.david.project;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Optional;

@SpringBootApplication
public class RestAdviceExampleApplication {

    private static final Logger logger = LoggerFactory.getLogger(RestAdviceExampleApplication.class);

    public static void main(String[] args) {
        System.setProperty("server.port", "8080");
        SpringApplication app = new SpringApplication(RestAdviceExampleApplication.class);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {

        String applicationName = env.getProperty("spring.application.name");
        String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
        String serverPort = env.getProperty("server.port");
        String contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path")).filter(StringUtils::isNotBlank).orElse("/");
        String baseUrl = protocol + "://localhost:" + serverPort + contextPath;

        logger.info("\n" +
                        "----------------------------------------------------------\n" +
                        "\tApplication '{}' is running! Access URLs:\n" +
                        "\tSwaggerUI:     {}swagger-ui/index.html\n" +
                        "----------------------------------------------------------",
                applicationName, baseUrl);

    }
}
