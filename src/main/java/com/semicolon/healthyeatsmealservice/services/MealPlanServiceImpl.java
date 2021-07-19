package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.data.models.*;
import com.semicolon.healthyeatsmealservice.data.repository.MealPlanRepository;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.exceptions.MealPlanException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import com.semicolon.healthyeatsmealservice.services.dtos.MealPlanDTO;
import com.semicolon.healthyeatsmealservice.services.dtos.MealScheduleDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private MealService mealService;

    @Autowired
    private ModelMapper modelMapper;

    private final int pageSize = 20;

    @Override
    public MealPlanDTO getMealPlanById(String mealPlanId) throws MealPlanException {
        MealPlan plan = getMealPlan(mealPlanId);
        return modelMapper.map(plan, MealPlanDTO.class);
    }

    @Override
    public MealPlanDTO getMealPlanByName(String mealPlanName) throws MealPlanException {
        MealPlan plan = mealPlanRepository.findMealPlanByName(mealPlanName)
                .orElseThrow(() -> new MealPlanException("No meal plan found with name: " + mealPlanName));
        return modelMapper.map(plan, MealPlanDTO.class);
    }

    @Override
    public List<MealPlanDTO> getAllMealPlans(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return mealPlanRepository.findAll(page)
                .stream()
                .map(mealPlan -> modelMapper.map(mealPlan, MealPlanDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MealPlanDTO createMealPlan(MealPlanDTO mealPlanDTO) throws MealException {
        MealPlan mealPlan = modelMapper.map(mealPlanDTO, MealPlan.class);
        mealPlan.setName(mealPlanDTO.getName().toLowerCase(Locale.ROOT));
        String specifiedMealPlanType = mealPlanDTO.getType();
        boolean foundType = false;
        for (MealPlanType type : MealPlanType.values()) {
            if (specifiedMealPlanType.equalsIgnoreCase(type.toString())) {
                mealPlan.setType(type);
                foundType = true;
                break;
            }
        }
        if (!foundType) {
            mealPlan.setType(MealPlanType.MONTHLY);
        }

        Map<Integer, MealScheduleDTO> submittedMeals = mealPlanDTO.getMeals();
        Map<Integer, MealSchedule> mealsToSave = mealPlan.getMeals();
        for (Integer day : submittedMeals.keySet()) {
            //Get submitted Ids from Database
            String breakFastId = submittedMeals.get(day).getBreakfast();
            String lunchId = submittedMeals.get(day).getLunch();
            String dinnerId = submittedMeals.get(day).getDinner();
            //Get Meals From Database
            Meal breakFast = null;
            Meal lunch = null;
            Meal dinner = null;
            if (breakFastId != null) {
                breakFast = mealService.internalGetMeal(breakFastId);
            }
            if (lunchId != null) {
                lunch = mealService.internalGetMeal(lunchId);
            }
            if (dinnerId != null) {
                dinner = mealService.internalGetMeal(dinnerId);
            }
            //Create meal Schedule for current day and set meals
            MealSchedule mealScheduleForCurrentDay = new MealSchedule();
            mealScheduleForCurrentDay.setBreakfast(breakFast);
            mealScheduleForCurrentDay.setLunch(lunch);
            mealScheduleForCurrentDay.setDinner(dinner);
            //add meal schedule to meals
            mealsToSave.put(day, mealScheduleForCurrentDay);
        }
        mealPlan.setMeals(mealsToSave);
        mealPlan = mealPlanRepository.save(mealPlan);
        return modelMapper.map(mealPlan, MealPlanDTO.class);
    }

    @Override
    public MealPlanDTO updateMealPlan(String planId, MealPlanDTO mealPlanDTO) throws MealPlanException {
        MealPlan mealPlanToUpdate = getMealPlan(planId);
        modelMapper.map(mealPlanDTO, mealPlanToUpdate);
        MealPlan updatedMealPlan = mealPlanRepository.save(mealPlanToUpdate);
        return modelMapper.map(updatedMealPlan, MealPlanDTO.class);
    }

    @Override
    public void deleteMealPlan(String planId) throws MealPlanException {
        MealPlan mealPlanToDelete = getMealPlan(planId);
        mealPlanRepository.delete(mealPlanToDelete);
    }


    private MealPlan getMealPlan(String planId) throws MealPlanException {
        return mealPlanRepository.findById(planId)
                .orElseThrow(() -> new MealPlanException("No meal plan found with Id: " + planId));
    }
}
