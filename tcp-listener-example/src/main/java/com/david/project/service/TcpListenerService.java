package com.david.project.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TcpListenerService {

    private ServerSocket serverSocket;

    private final ExecutorService acceptPool = Executors.newSingleThreadExecutor();

    private final ExecutorService workerPool = Executors.newCachedThreadPool();

    private volatile boolean running = true;

    private final String host = "127.0.0.1";

    private final int port = 9000;

    @PostConstruct
    public void start() {
        acceptPool.submit(() -> {
            try {
                serverSocket = new ServerSocket(port, 50, InetAddress.getByName(host));
                System.out.println("TCP Listener started on " + host + ":" + port);
                while (running && !serverSocket.isClosed()) {
                    Socket clientSocket = serverSocket.accept();
                    workerPool.submit(() -> handleClient(clientSocket));
                }
            } catch (Exception e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleClient(Socket client) {
        String clientInfo = client.getRemoteSocketAddress().toString();
        System.out.println("Client connected: " + clientInfo);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Received from " + clientInfo + ": " + line);
                out.println("Echo: " + line);
                if ("bye".equalsIgnoreCase(line.trim())) {
                    out.println("Goodbye");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error handling client " + clientInfo + ": " + e.getMessage());
        } finally {
            try {
                client.close();
                System.out.println("Client disconnected: " + clientInfo);
            } catch (Exception e) {
                System.out.println("Error closing client socket " + clientInfo + ": " + e.getMessage());
            }
        }
    }

    @PreDestroy
    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception ignore) {}
        acceptPool.shutdownNow();
        workerPool.shutdownNow();
        System.out.println("TCP Listener stopped.");
    }
}
