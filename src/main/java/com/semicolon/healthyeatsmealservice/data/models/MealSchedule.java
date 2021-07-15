package com.semicolon.healthyeatsmealservice.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashMap;
import java.util.Map;

@Data
public class MealSchedule {
    @DBRef
    private Map<MealTime, Meal> schedule = new HashMap<>();
}
