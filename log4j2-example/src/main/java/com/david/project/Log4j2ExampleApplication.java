package com.david.project;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Optional;

@SpringBootApplication
public class Log4j2ExampleApplication {

    private static final Logger logger = LogManager.getLogger(Log4j2ExampleApplication.class);

    public static void main(String[] args) {
        System.setProperty("server.port", "8080");
        SpringApplication app = new SpringApplication(Log4j2ExampleApplication.class);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {

        String[] profiles = env.getActiveProfiles().length == 0 ? env.getDefaultProfiles() : env.getActiveProfiles();
        String applicationName = env.getProperty("spring.application.name");
        String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
        String serverPort = env.getProperty("server.port");
        String contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path")).filter(StringUtils::isNotBlank).orElse("/");
        String baseUrl = protocol + "://localhost:" + serverPort + contextPath;
        String configServerStatus = StringUtils.defaultIfEmpty(env.getProperty("configserver.status"), "Not found or not setup for this application");

        logger.info("\n" +
                        "----------------------------------------------------------\n" +
                        "\tApplication '{}' is running! Access URLs:\n" +
                        "\tLocal:         {}\n" +
                        "\tH2 DB Console: {}h2-console\n" +
                        "\tSwaggerUI:     {}swagger-ui/index.html\n" +
                        "----------------------------------------------------------",
                applicationName, baseUrl, baseUrl, baseUrl);

    }

}
