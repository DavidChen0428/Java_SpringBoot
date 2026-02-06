package com.david.project.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class MonitorService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedDelayString = "30000") // every 30 seconds
    public void poll() {
        try {
            Map resp = restTemplate.getForObject("http://192.168.0.10:9001/actuator/health", Map.class);
            System.out.println("health: " + resp.get("status"));
        } catch (Exception ex) {
            System.err.println("Error during polling: " + ex.getMessage());
        }
    }
}
