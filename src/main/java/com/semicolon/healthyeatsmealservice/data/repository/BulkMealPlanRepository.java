package com.semicolon.healthyeatsmealservice.data.repository;

import com.semicolon.healthyeatsmealservice.data.models.BulkMealPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BulkMealPlanRepository extends MongoRepository<BulkMealPlan, String> {

}
