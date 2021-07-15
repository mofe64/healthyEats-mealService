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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class MealRepositoryTest {
    @Autowired
    MealRepository mealRepository;
    Meal meal;
    Meal meal1;

    @BeforeEach
    void setUp() {
        meal = new Meal();
        meal1 = new Meal();
        meal.setName("test meal");
        meal1.setName("test meal 1");
        Pageable page = PageRequest.of(0, 10);
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
    }

    @Test
    void findMealByPriceGreaterThanEqual() {
    }

    @Test
    void findMealByPriceLessThanEqual() {
    }
}