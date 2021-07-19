package com.semicolon.healthyeatsmealservice.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    private String id;
}
