package com.semicolon.healthyeatsmealservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("")
    public String mealServiceStartUP() {
        return "Meal-Service Up and Running ......";
    }
}
