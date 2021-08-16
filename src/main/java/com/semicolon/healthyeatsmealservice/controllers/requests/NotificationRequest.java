package com.semicolon.healthyeatsmealservice.controllers.requests;

import lombok.Data;

@Data
public class NotificationRequest {
    private String title;
    private String addressee;
    private String sender;
}
