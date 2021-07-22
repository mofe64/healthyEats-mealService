package com.semicolon.healthyeatsmealservice.services.dtos;

import com.semicolon.healthyeatsmealservice.data.models.MealPlanType;
import com.semicolon.healthyeatsmealservice.data.models.MealSchedule;
import com.semicolon.healthyeatsmealservice.data.models.User;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MealPlanResponseDTO {
    private String id;
    private String name;
    private int durationInDays;
    private boolean active = true;
    private boolean dailyDropOff;
    private boolean weeklyDropOff;
    private MealPlanType type;
    private Map<Integer, MealSchedule> meals = new HashMap<>();
    private boolean custom;
    private List<User> subscribedUsers;
}
