package com.semicolon.healthyeatsmealservice.controllers;

import com.semicolon.healthyeatsmealservice.controllers.responses.APIResponse;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.exceptions.MealPlanException;
import com.semicolon.healthyeatsmealservice.services.MealPlanService;
import com.semicolon.healthyeatsmealservice.services.dtos.MealPlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mealService/api/v1/mealPlans")
public class MealPlanController {
    @Autowired
    private MealPlanService mealPlanService;


    @GetMapping("")
    public ResponseEntity<?> getMealPlans(@RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            page = 0;
        }
        List<MealPlanDTO> mealPlans = mealPlanService.getAllMealPlans(page);
        return new ResponseEntity<>(mealPlans, HttpStatus.OK);
    }

    @GetMapping("{planId}")
    public ResponseEntity<?> getMealPlan(@PathVariable String planId) {
        try {
            MealPlanDTO mealPlan = mealPlanService.getMealPlanById(planId);
            return new ResponseEntity<>(mealPlan, HttpStatus.OK);
        } catch (MealPlanException exception) {
            return new ResponseEntity<>(new APIResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(new APIResponse(false, "Looks like something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createMealPlan(@RequestBody MealPlanDTO mealPlanDTO) {
        try {
            MealPlanDTO savedMealPlan = mealPlanService.createMealPlan(mealPlanDTO);
            return new ResponseEntity<>(savedMealPlan, HttpStatus.CREATED);
        } catch (MealException exception) {
            return new ResponseEntity<>(new APIResponse(false, "Looks like something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("{planId}")
    public ResponseEntity<?> updateMealPlan(@PathVariable String planId, @RequestBody MealPlanDTO mealPlanDTO) {
        try {
            MealPlanDTO updatedMealPlan = mealPlanService.updateMealPlan(planId, mealPlanDTO);
            return new ResponseEntity<>(updatedMealPlan, HttpStatus.OK);
        } catch (MealPlanException exception) {
            return new ResponseEntity<>(new APIResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(new APIResponse(false, "Looks like something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{planId}")
    public ResponseEntity<?> deleteMealPlan(@PathVariable String planId) {
        try {
            mealPlanService.deleteMealPlan(planId);
            return new ResponseEntity<>(new APIResponse(true, "Meal Plan deleted successfully"), HttpStatus.NO_CONTENT);
        } catch (MealPlanException exception) {
            return new ResponseEntity<>(new APIResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(new APIResponse(false, "Looks like something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
