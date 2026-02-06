package com.david.project.health;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;

@Component("pingIndicator")
public class PingHealthIndicator implements HealthIndicator {

    private final String host = "192.168.0.10";

    private final int port = 9001;

    private final int timeoutMs = 1000;

    @Override
    public @Nullable Health health() {
        Instant start = Instant.now();
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeoutMs);
            Duration took = Duration.between(start, Instant.now());
            return Health.up()
                    .withDetail("targetHost", host)
                    .withDetail("targetPort", port)
                    .withDetail("responseTimeMs", took.toMillis())
                    .build();
        } catch (Exception ex) {
            Duration took = Duration.between(start, Instant.now());
            return Health.down(ex)
                    .withDetail("targetHost", host)
                    .withDetail("targetPort", port)
                    .withDetail("responseTimeMs", took.toMillis())
                    .withDetail("error", ex.getClass().getSimpleName())
                    .withDetail("message", ex.getMessage())
                    .build();
        }
    }
}
