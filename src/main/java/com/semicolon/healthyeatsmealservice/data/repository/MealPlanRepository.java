package com.semicolon.healthyeatsmealservice.data.repository;

import com.semicolon.healthyeatsmealservice.data.models.MealPlan;
import com.semicolon.healthyeatsmealservice.data.models.MealPlanType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealPlanRepository extends MongoRepository<MealPlan, String> {
    Optional<MealPlan> findMealPlanByName(String name);

    List<MealPlan> findMealPlanByActiveIsFalse(Pageable page);

    List<MealPlan> findMealPlanByActiveIsTrue(Pageable page);

    List<MealPlan> findMealPlanByDailyDropOffIsTrue(Pageable page);

    List<MealPlan> findMealPlanByWeeklyDropOffIsTrue(Pageable page);

    List<MealPlan> findMealPlanByCustomIs(boolean isCustom, Pageable page);


    List<MealPlan> findMealPlanByType(MealPlanType type, Pageable page);

}
