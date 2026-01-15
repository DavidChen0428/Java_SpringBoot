package com.david.project.rest.api;

import com.david.project.rest.api.vm.EmailTemplateRequest;
import com.david.project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-template")
    public ResponseEntity<?> sendEmail(@RequestBody EmailTemplateRequest request) {

        try {
            emailService.sendTemplateEmail(request.getTo(), request.getSubject(), request.getModel());
            return ResponseEntity.ok().body("Email queued/sent");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Failed to send email: %s".formatted(ex.getMessage()));
        }
    }
}
