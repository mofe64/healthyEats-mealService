package com.semicolon.healthyeatsmealservice.controllers;

import com.semicolon.healthyeatsmealservice.controllers.requests.FilterRequest;
import com.semicolon.healthyeatsmealservice.controllers.responses.APIResponse;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.services.MealService;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mealService/api/v1/meals")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("")
    public ResponseEntity<?> getAllMeals(@RequestParam(name = "page", required = false) Integer page) {
//        log.info(roles);
        if (page == null) {
            page = 0;
        }
        List<MealDTO> meals = mealService.getAllMeals(page);
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<?> mealSearch(@PathVariable String query) {
        int pageNumber = 0;
        List<MealDTO> searchResults = mealService.searchMeal(query, pageNumber);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @GetMapping("/search/{query}/{page}")
    public ResponseEntity<?> mealSearch(@PathVariable String query, @PathVariable int page) {
        List<MealDTO> searchResults = mealService.searchMeal(query, page);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @GetMapping("/search/exact/{query}")
    public ResponseEntity<?> exactMealSearch(@PathVariable String query) {
        try {
            MealDTO meal = mealService.findMealByName(query);
            return new ResponseEntity<>(meal, HttpStatus.OK);
        } catch (MealException exception) {
            return new ResponseEntity<>(new APIResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(new APIResponse(false, "Looks like something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterMeals(@RequestBody FilterRequest filterRequest) {
        List<MealDTO> filteredMeals = mealService.filterMeals(filterRequest, 0);
        return new ResponseEntity<>(filteredMeals, HttpStatus.OK);
    }

    @PostMapping("/filter/{pageNo}")
    public ResponseEntity<?> filterMeals(@PathVariable int pageNo, @RequestBody FilterRequest filterRequest) {
        List<MealDTO> filteredMeals = mealService.filterMeals(filterRequest, pageNo);
        return new ResponseEntity<>(filteredMeals, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createMeal(@RequestBody MealDTO newMeal) {
        MealDTO savedMeal = mealService.createMeal(newMeal);
        return new ResponseEntity<>(savedMeal, HttpStatus.CREATED);
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<?> getAMeal(@PathVariable String mealId) {
        try {
            MealDTO meal = mealService.findMealById(mealId);
            return new ResponseEntity<>(meal, HttpStatus.OK);
        } catch (MealException exception) {
            return new ResponseEntity<>(new APIResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(new APIResponse(false, "Looks like something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{mealId}")
    public ResponseEntity<?> updateMeal(@PathVariable String mealId, @RequestBody MealDTO updateDetails) {
        try {
            MealDTO updatedMeal = mealService.updateMeal(mealId, updateDetails);
            return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
        } catch (MealException exception) {
            return new ResponseEntity<>(new APIResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(new APIResponse(false, "Looks like something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{mealId}")
    public ResponseEntity<?> deleteMeal(@PathVariable String mealId) {
        try {
            mealService.deleteMeal(mealId);
            return new ResponseEntity<>(new APIResponse(true, "Meal deleted successfully"), HttpStatus.NO_CONTENT);
        } catch (MealException exception) {
            return new ResponseEntity<>(new APIResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(new APIResponse(false, "Looks like something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
