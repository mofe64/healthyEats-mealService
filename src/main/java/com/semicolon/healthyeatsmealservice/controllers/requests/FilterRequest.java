package com.semicolon.healthyeatsmealservice.controllers.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FilterRequest {
    private boolean filterByCalorie;
    private boolean filterByPrice;
    private boolean filterLess;
    private int calorieCount;
    private double price;
}
