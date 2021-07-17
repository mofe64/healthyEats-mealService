package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.data.models.MealPlan;
import com.semicolon.healthyeatsmealservice.data.repository.MealPlanRepository;
import com.semicolon.healthyeatsmealservice.exceptions.MealPlanException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import com.semicolon.healthyeatsmealservice.services.dtos.MealPlanDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    private MealPlanRepository mealPlanRepository;

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
    public MealPlanDTO createMealPlan(MealPlanDTO mealPlanDTO) {
        MealPlan mealPlan = modelMapper.map(mealPlanDTO, MealPlan.class);
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
