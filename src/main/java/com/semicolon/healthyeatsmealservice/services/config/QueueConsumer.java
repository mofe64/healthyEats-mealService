package com.semicolon.healthyeatsmealservice.services.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.healthyeatsmealservice.controllers.requests.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueConsumer {
    public void receiveMessage(String message) {
        log.info("message recieved ...");
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        log.info("Received (No String) " + strMessage);
        processMessage(strMessage);
    }

    private void processMessage(String message) {
        try {
            NotificationRequest notification = new ObjectMapper().readValue(message, NotificationRequest.class);
            log.info("recieved notification --> {}", notification);
        } catch (Exception exception) {
            log.info("something went wrong ...");
            log.info(exception.getMessage());
        }
    }
}
