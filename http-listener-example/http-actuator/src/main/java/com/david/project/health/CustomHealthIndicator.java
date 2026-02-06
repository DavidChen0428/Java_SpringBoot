package com.david.project.health;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

// 檢查外部 API 或 業務邏輯
@Component("customIndicator")
public class CustomHealthIndicator implements HealthIndicator {


    @Override
    public @Nullable Health health() {
        Instant start = Instant.now();
        try {
            // 範例：檢查某個外部 HTTP 依賴是否可達（可改成實際業務檢查）
            URL url = new URL("https://example-external-service.local/health");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(2000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();

            Duration took = Duration.between(start, Instant.now());
            if (code >= 200 && code < 300) {
                return Health.up()
                        .withDetail("externalService", "reachable")
                        .withDetail("statusCode", code)
                        .withDetail("responseTimeMs", took.toMillis())
                        .build();
            } else {
                return Health.down()
                        .withDetail("externalService", "unhealthy")
                        .withDetail("statusCode", code)
                        .withDetail("responseTimeMs", took.toMillis())
                        .build();
            }
        } catch (Exception ex) {
            Duration took = Duration.between(start, Instant.now());
            return Health.down(ex)
                    .withDetail("externalService", "error")
                    .withDetail("exception", ex.getClass().getSimpleName())
                    .withDetail("message", ex.getMessage())
                    .withDetail("responseTimeMs", took.toMillis())
                    .build();
        }
    }
}
