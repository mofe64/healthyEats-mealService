package com.semicolon.healthyeatsmealservice.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class MealPlan {
    @Id
    private String id;
    private String name;
    private int durationInDays;
    private boolean active;
    private boolean dailyDropOff;
    private boolean weeklyDropOff;
    private MealPlanType type;
    private Map<Integer, MealSchedule> meals;
    private boolean isCustom;

}
