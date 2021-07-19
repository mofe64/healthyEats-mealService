package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.controllers.requests.FilterRequest;
import com.semicolon.healthyeatsmealservice.data.models.Meal;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealService {
    MealDTO createMeal(MealDTO mealDTO);
    MealDTO updateMeal(String mealId, MealDTO updatedMealDetails) throws MealException;
    void deleteMeal(String mealId) throws MealException;
    MealDTO findMealById(String mealId) throws MealException;
    MealDTO findMealByName(String mealName) throws MealException;
    List<MealDTO> getAllMeals(int pageNumber);
    List<MealDTO> searchMeal(String query, int pageNumber);
    List<MealDTO> filterMeals(FilterRequest filterRequest, int pageNumber);
    Meal internalGetMeal(String mealId) throws MealException;
    //todo orderMeal, getMealsMatchingPreference;
}
