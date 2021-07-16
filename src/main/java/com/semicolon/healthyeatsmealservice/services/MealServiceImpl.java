package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.data.models.Meal;
import com.semicolon.healthyeatsmealservice.data.repository.MealRepository;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public MealDTO createMeal(MealDTO mealDTO) {
        Meal mealObj = modelMapper.map(mealDTO, Meal.class);
        Meal savedMeal = mealRepository.save(mealObj);
        return modelMapper.map(savedMeal, MealDTO.class);
    }

    @Override
    public MealDTO updateMeal(String mealId, MealDTO updatedMealDetails) throws MealException {
        Meal mealToUpdate = findMealByMealId(mealId);
        modelMapper.map(updatedMealDetails, mealToUpdate);
        Meal updatedMeal = mealRepository.save(mealToUpdate);
        return modelMapper.map(updatedMeal, MealDTO.class);
    }

    @Override
    public void deleteMeal(String mealId) throws MealException {
        Meal mealToDelete = findMealByMealId(mealId);
        mealRepository.delete(mealToDelete);
    }

    @Override
    public MealDTO findMealById(String mealId) throws MealException {
        Meal meal = findMealByMealId(mealId);
        return modelMapper.map(meal, MealDTO.class);
    }

    @Override
    public MealDTO findMealByName(String mealName) throws MealException {
        Meal meal = mealRepository.findMealByName(mealName)
                .orElseThrow(() -> new MealException("No Meal found with name: " + mealName));
        return modelMapper.map(meal, MealDTO.class);

    }

    private Meal findMealByMealId(String id) throws MealException {
        return mealRepository.findById(id)
                .orElseThrow(() -> new MealException("No meal found with Id: " + id));
    }

}
