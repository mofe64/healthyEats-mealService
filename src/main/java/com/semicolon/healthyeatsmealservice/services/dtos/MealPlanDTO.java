package com.semicolon.healthyeatsmealservice.services.dtos;

import com.semicolon.healthyeatsmealservice.data.models.MealPlanType;
import com.semicolon.healthyeatsmealservice.data.models.MealSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealPlanDTO {
    private String id;
    private String name;
    private int durationInDays;
    private boolean active;
    private boolean dailyDropOff;
    private boolean weeklyDropOff;
    private MealPlanType type;
    private Map<Integer, MealSchedule> meals;
    private boolean custom;
}
