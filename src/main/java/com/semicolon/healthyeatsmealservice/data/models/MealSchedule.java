package com.semicolon.healthyeatsmealservice.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;


@Data
public class MealSchedule {
    @DBRef
    private Meal breakfast;
    @DBRef
    private Meal lunch;
    @DBRef
    private Meal dinner;
}
