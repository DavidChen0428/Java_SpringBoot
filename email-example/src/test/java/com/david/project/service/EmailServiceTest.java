package com.david.project.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SpringBootTest
public class EmailServiceTest {

    private final Logger logger = LoggerFactory.getLogger(EmailServiceTest.class);

    private final EmailService emailService;

    @Autowired
    public EmailServiceTest(EmailService emailService) {
        this.emailService = emailService;
    }

    @Test
    void sendTemplateEmail() {
        String to = "davidchen428@yahoo.com";
        String subject = "SpringBoot Email Project Test: %s".formatted(transferDataStr(Instant.now()));
        Map<String, Object> model = Map.of("name", "David Chen");

        try {
            emailService.sendTemplateEmail(to, subject, model);
        } catch (Exception ex) {
            logger.error("Failed to send email: {}", ex.getMessage());
        }


    }

    private String transferDataStr(Instant date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Taipei"));
        return formatter.format(date);
    }
}
