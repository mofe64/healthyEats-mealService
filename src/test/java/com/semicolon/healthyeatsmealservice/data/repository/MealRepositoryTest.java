package com.semicolon.healthyeatsmealservice.data.repository;

import com.semicolon.healthyeatsmealservice.data.models.Meal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class MealRepositoryTest {
    @Autowired
    MealRepository mealRepository;
    Meal meal;
    Meal meal1;
    Pageable page;

    @BeforeEach
    void setUp() {
        meal = new Meal();
        meal1 = new Meal();
        meal.setName("test meal");
        meal1.setName("test meal 1");
        page = PageRequest.of(0, 10);
    }

    @AfterEach
    void tearDown() {
        mealRepository.deleteAll();
    }

    @Test
    void findMealByName() {
        mealRepository.save(meal);
        mealRepository.save(meal1);
        Optional<Meal> mealOptional = mealRepository.findMealByName("test meal");
        assertThat(mealOptional.isPresent()).isTrue();
        assertThat(mealOptional.get().getName()).isEqualTo("test meal");
    }

    @Test
    void findMealByNameContaining() {
        mealRepository.save(meal);
        mealRepository.save(meal1);
        List<Meal> meals = mealRepository.findMealByNameContaining("1", page);
        assertThat(meals).isNotEmpty();
        assertThat(meals.size()).isEqualTo(1);
        assertThat(meals.get(0).getName()).isEqualTo("test meal 1");
    }

    @Test
    void findMealByPriceGreaterThanEqual() {
        meal.setPrice(BigDecimal.valueOf(100));
        meal1.setPrice(BigDecimal.valueOf(110));
        mealRepository.save(meal);
        mealRepository.save(meal1);
        List<Meal> meals = mealRepository.findMealByPriceGreaterThanEqual(BigDecimal.valueOf(110), page);
        assertThat(meals).isNotEmpty();
        assertThat(meals).hasSize(1);
        assertThat(meals.get(0).getName()).isEqualTo("test meal 1");
    }

    @Test
    void findMealByPriceLessThanEqual() {
        meal.setPrice(BigDecimal.valueOf(200));
        meal1.setPrice(BigDecimal.valueOf(20));
        mealRepository.save(meal1);
        mealRepository.save(meal);
        List<Meal> meals = mealRepository.findMealByPriceLessThanEqual(BigDecimal.valueOf(20), page);
        assertThat(meals).isNotEmpty();
        assertThat(meals).hasSize(1);
        assertThat(meals.get(0).getName()).isEqualTo("test meal 1");
    }

    @Test
    void findMealByCalorieCountLessThanEqual() {
        meal.setCalorieCount(100);
        meal1.setCalorieCount(200);
        mealRepository.save(meal);
        mealRepository.save(meal1);
        List<Meal> meals = mealRepository.findMealByCalorieCountLessThanEqual(100, page);
        assertThat(meals).isNotEmpty();
        assertThat(meals).hasSize(1);
        assertThat(meals.get(0).getName()).isEqualTo("test meal");
    }

    @Test
    void findMealByCalorieCountGreaterThanEqual() {
        meal.setCalorieCount(100);
        meal1.setCalorieCount(200);
        mealRepository.save(meal1);
        mealRepository.save(meal);
        List<Meal> meals = mealRepository.findMealByCalorieCountGreaterThanEqual(200, page);
        assertThat(meals).isNotEmpty();
        assertThat(meals).hasSize(1);
        assertThat(meals.get(0).getName()).isEqualTo("test meal 1");

    }
}