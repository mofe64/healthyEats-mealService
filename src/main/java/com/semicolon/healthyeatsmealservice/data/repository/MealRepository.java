package com.semicolon.healthyeatsmealservice.data.repository;

import com.semicolon.healthyeatsmealservice.data.models.Meal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository  extends MongoRepository<Meal, String> {
    Optional<Meal> findMealByName(String meal);
    List<Meal> findMealByNameContaining(String searchPhrase, Pageable page);
    List<Meal> findMealByPriceGreaterThanEqual(BigDecimal price, Pageable page);
    List<Meal> findMealByPriceLessThanEqual(BigDecimal price, Pageable page);
    List<Meal> findMealByPriceGreaterThan(BigDecimal price, Pageable page);
    List<Meal> findMealByCalorieCountLessThanEqual(Integer calorieCount, Pageable page);
    List<Meal> findMealByCalorieCountGreaterThanEqual(Integer calorieCount, Pageable page);

}
