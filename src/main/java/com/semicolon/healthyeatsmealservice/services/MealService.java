package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import org.springframework.stereotype.Service;

@Service
public interface MealService {
    MealDTO createMeal(MealDTO mealDTO);
    MealDTO updateMeal(String mealId, MealDTO updatedMealDetails) throws MealException;
    void deleteMeal(String mealId) throws MealException;
    MealDTO findMealById(String mealId) throws MealException;
    MealDTO findMealByName(String mealName) throws MealException;
    //todo orderMeal, getMealsMatchingPreference;
}
