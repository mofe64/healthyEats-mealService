package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.controllers.requests.FilterRequest;
import com.semicolon.healthyeatsmealservice.data.models.Meal;
import com.semicolon.healthyeatsmealservice.data.repository.MealRepository;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final int pageSize = 20;


    @Override
    public MealDTO createMeal(MealDTO mealDTO) {
        Meal mealObj = modelMapper.map(mealDTO, Meal.class);
        mealObj.setName(mealObj.getName().toLowerCase(Locale.ROOT));
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
        Meal meal = mealRepository.findMealByName(mealName.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new MealException("No Meal found with name: " + mealName));
        return modelMapper.map(meal, MealDTO.class);

    }

    @Override
    public List<MealDTO> getAllMeals(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return mealRepository.findAll(page)
                .stream()
                .map(meal -> modelMapper.map(meal, MealDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MealDTO> searchMeal(String query, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return mealRepository.findMealByNameContaining(query.toLowerCase(Locale.ROOT), page)
                .stream()
                .map(meal -> modelMapper.map(meal, MealDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MealDTO> filterMeals(FilterRequest filterRequest, int pageNumber) {
        log.info("filter request is --> {}", filterRequest);
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<MealDTO> filterResults = new ArrayList<>();
        if (filterRequest.isFilterByCalorie()) {
            int calorieCount = filterRequest.getCalorieCount();
            if (filterRequest.isFilterLess()) {
                filterResults = mealRepository.findMealByCalorieCountLessThanEqual(calorieCount, page)
                        .stream()
                        .map(meal -> modelMapper.map(meal, MealDTO.class))
                        .collect(Collectors.toList());
            } else {
                filterResults = mealRepository.findMealByCalorieCountGreaterThanEqual(calorieCount, page)
                        .stream()
                        .map(meal -> modelMapper.map(meal, MealDTO.class))
                        .collect(Collectors.toList());
            }
        } else if (filterRequest.isFilterByPrice()) {
                BigDecimal price = BigDecimal.valueOf(filterRequest.getPrice());
            if (filterRequest.isFilterLess()) {
                filterResults = mealRepository.findMealByPriceLessThanEqual(price, page)
                        .stream()
                        .map(meal -> modelMapper.map(meal, MealDTO.class))
                        .collect(Collectors.toList());
            } else {
                filterResults = mealRepository.findMealByPriceGreaterThanEqual(price, page)
                        .stream()
                        .map(meal -> modelMapper.map(meal, MealDTO.class))
                        .collect(Collectors.toList());
            }
        }
        return filterResults;
    }

    private Meal findMealByMealId(String id) throws MealException {
        return mealRepository.findById(id)
                .orElseThrow(() -> new MealException("No meal found with Id: " + id));
    }


}
