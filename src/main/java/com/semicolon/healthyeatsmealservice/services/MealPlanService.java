package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.data.models.Meal;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.exceptions.MealPlanException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import com.semicolon.healthyeatsmealservice.services.dtos.MealPlanDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealPlanService {
    MealPlanDTO getMealPlanById(String mealPlanId) throws MealPlanException;
    MealPlanDTO getMealPlanByName(String mealPlanName) throws MealPlanException;
    List<MealPlanDTO> getAllMealPlans(int pageNumber);
    MealPlanDTO createMealPlan(MealPlanDTO mealPlanDTO) throws MealException;
    MealPlanDTO updateMealPlan(String planId, MealPlanDTO mealPlanDTO) throws MealPlanException;
    void deleteMealPlan(String planId) throws MealPlanException;
    //todo getMealPlansMatchingPreference;
}
