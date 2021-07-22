package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.data.models.Meal;
import com.semicolon.healthyeatsmealservice.data.models.MealPlan;
import com.semicolon.healthyeatsmealservice.data.models.User;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.exceptions.MealPlanException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import com.semicolon.healthyeatsmealservice.services.dtos.MealPlanDTO;
import com.semicolon.healthyeatsmealservice.services.dtos.MealPlanResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealPlanService {
    MealPlanResponseDTO getMealPlanById(String mealPlanId) throws MealPlanException;
    MealPlanResponseDTO getMealPlanByName(String mealPlanName) throws MealPlanException;
    List<MealPlanResponseDTO> getAllMealPlans(int pageNumber);
    MealPlanResponseDTO createMealPlan(MealPlanDTO mealPlanDTO, boolean isCustom) throws MealException;
    MealPlanResponseDTO updateMealPlan(String planId, MealPlanDTO mealPlanDTO) throws MealPlanException;
    void deleteMealPlan(String planId) throws MealPlanException;
    MealPlan subscribeUserToMealPlan(User user, String planId) throws MealPlanException;
    MealPlan unsubscribeUserFromMealPlan(User user, String plantId) throws MealPlanException;
    //todo getMealPlansMatchingPreference;
}
