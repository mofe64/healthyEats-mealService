package com.semicolon.healthyeatsmealservice.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private boolean active = true;
    private boolean dailyDropOff;
    private boolean weeklyDropOff;
    private MealPlanType type;
    private Map<Integer, MealSchedule> meals = new HashMap<>();
    private boolean custom;
    @DBRef(db = "healthy-Eats-users-db")
    private List<User> subscribedUsers;

    public void addUser(User user) {
        if (subscribedUsers == null) {
            subscribedUsers = new ArrayList<>();
        }
        subscribedUsers.add(user);
    }

//    public void addUsers(List<User> users) {
//        if (subscribedUsers == null) {
//            subscribedUsers = new ArrayList<>();
//        }
//        subscribedUsers.addAll(users);
//    }

    public void removeUser(User user) {
        if(subscribedUsers == null){
            return;
        }
        subscribedUsers.remove(user);
    }

}
