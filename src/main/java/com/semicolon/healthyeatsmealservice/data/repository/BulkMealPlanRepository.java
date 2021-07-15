package com.semicolon.healthyeatsmealservice.data.repository;

import com.semicolon.healthyeatsmealservice.data.models.BulkMealPlan;
import com.semicolon.healthyeatsmealservice.data.models.MealPlanType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BulkMealPlanRepository extends MongoRepository<BulkMealPlan, String> {
    Optional<BulkMealPlan> findBulkMealPlanByName(String name);

    List<BulkMealPlan> findBulkMealPlanByActiveIsFalse(Pageable page);

    List<BulkMealPlan> findBulkMealPlanByActiveIsTrue(Pageable page);

    List<BulkMealPlan> findBulkMealPlanByDailyDropOffIsTrue(Pageable page);

    List<BulkMealPlan> findBulkMealPlanByWeeklyDropOffIsTrue(Pageable page);

    List<BulkMealPlan> findBulkMealPlanByCustomIsTrue(Pageable page);

    List<BulkMealPlan> findBulkMealPlanByCustomIsFalse(Pageable page);

    List<BulkMealPlan> findBulkMealPlanByType(MealPlanType type, Pageable page);
}
