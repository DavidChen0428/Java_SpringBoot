package com.david.project.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private final Configuration freemarkerConfig;

    @Autowired
    public EmailService(JavaMailSender mailSender, Configuration freemarkerConfig) {
        this.mailSender = mailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    public void sendTemplateEmail(String to, String subject, Map<String, Object> model) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("your_email@gmail.com");

        Template template = freemarkerConfig.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setText(html, true);
        mailSender.send(message);
    }

}
