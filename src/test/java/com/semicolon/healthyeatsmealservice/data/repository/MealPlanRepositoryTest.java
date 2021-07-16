package com.semicolon.healthyeatsmealservice.data.repository;

import com.semicolon.healthyeatsmealservice.data.models.MealPlan;
import com.semicolon.healthyeatsmealservice.data.models.MealPlanType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataMongoTest
class MealPlanRepositoryTest {
    @Autowired
    MealPlanRepository mealPlanRepository;
    MealPlan veganMealPlan;
    MealPlan weightLossMealPlan;
    Pageable page;
    @BeforeEach
    void setUp() {
        veganMealPlan = new MealPlan();
        veganMealPlan.setName("vegan");
        weightLossMealPlan = new MealPlan();
        weightLossMealPlan.setName("weightLoss");
        page = PageRequest.of(0, 10);
    }

    @AfterEach
    void tearDown() {
        mealPlanRepository.deleteAll();
    }

    @Test
    void findMealPlanByName() {
        mealPlanRepository.save(veganMealPlan);
        mealPlanRepository.save(weightLossMealPlan);
        Optional<MealPlan> mealPlan = mealPlanRepository.findMealPlanByName("vegan");
        assertThat(mealPlan.isPresent()).isTrue();
        assertThat(mealPlan.get().getName()).isEqualTo("vegan");
    }

    @Test
    void findMealPlanByActiveIsFalse() {
        veganMealPlan.setActive(false);
        weightLossMealPlan.setActive(true);
        mealPlanRepository.save(veganMealPlan);
        mealPlanRepository.save(weightLossMealPlan);
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlanByActiveIsFalse(page);
        assertThat(mealPlans).isNotEmpty();
        assertThat(mealPlans).hasSize(1);
        assertThat(mealPlans.get(0).getName()).isEqualTo("vegan");
    }

    @Test
    void findMealPlanByActiveIsTrue() {
        veganMealPlan.setActive(true);
        weightLossMealPlan.setActive(false);
        mealPlanRepository.save(veganMealPlan);
        mealPlanRepository.save(weightLossMealPlan);
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlanByActiveIsTrue(page);
        assertThat(mealPlans).isNotEmpty();
        assertThat(mealPlans).hasSize(1);
        assertThat(mealPlans.get(0).getName()).isEqualTo("vegan");
    }

    @Test
    void findMealPlanByDailyDropOffIsTrue() {
        veganMealPlan.setDailyDropOff(true);
        weightLossMealPlan.setDailyDropOff(false);
        mealPlanRepository.save(veganMealPlan);
        mealPlanRepository.save(weightLossMealPlan);
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlanByDailyDropOffIsTrue(page);
        assertThat(mealPlans).isNotEmpty();
        assertThat(mealPlans).hasSize(1);
        assertThat(mealPlans.get(0).getName()).isEqualTo("vegan");
    }

    @Test
    void findMealPlanByWeeklyDropOffIsTrue() {
        veganMealPlan.setWeeklyDropOff(true);
        weightLossMealPlan.setWeeklyDropOff(false);
        mealPlanRepository.save(veganMealPlan);
        mealPlanRepository.save(weightLossMealPlan);
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlanByWeeklyDropOffIsTrue(page);
        assertThat(mealPlans).isNotEmpty();
        assertThat(mealPlans).hasSize(1);
        assertThat(mealPlans.get(0).getName()).isEqualTo("vegan");
    }

    @Test
    void findMealPlanByCustomIsTrue() {
        veganMealPlan.setCustom(true);
        weightLossMealPlan.setCustom(false);
        mealPlanRepository.save(veganMealPlan);
        mealPlanRepository.save(weightLossMealPlan);
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlanByCustomIs(true, page);
        assertThat(mealPlans).isNotEmpty();
        assertThat(mealPlans).hasSize(1);
        assertThat(mealPlans.get(0).getName()).isEqualTo("vegan");
    }

    @Test
    void findMealPlanByCustomIsFalse() {
        veganMealPlan.setCustom(false);
        weightLossMealPlan.setCustom(true);
        mealPlanRepository.save(veganMealPlan);
        mealPlanRepository.save(weightLossMealPlan);
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlanByCustomIs( false,page);
        assertThat(mealPlans).isNotEmpty();
        assertThat(mealPlans).hasSize(1);
        assertThat(mealPlans.get(0).getName()).isEqualTo("vegan");
    }

    @Test
    void findMealPlanByType() {
        veganMealPlan.setType(MealPlanType.DAILY);
        weightLossMealPlan.setType(MealPlanType.MONTHLY);
        mealPlanRepository.save(veganMealPlan);
        mealPlanRepository.save(weightLossMealPlan);
        List<MealPlan> mealPlans = mealPlanRepository.findMealPlanByType(MealPlanType.DAILY, page);
        assertThat(mealPlans).isNotEmpty();
        assertThat(mealPlans).hasSize(1);
        assertThat(mealPlans.get(0).getName()).isEqualTo("vegan");
    }
}