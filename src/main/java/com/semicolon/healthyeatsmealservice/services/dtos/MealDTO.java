package com.semicolon.healthyeatsmealservice.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealDTO {
    private String id;
    @NotBlank(message = "meal name cannot be blank")
    private String name;
    @NotBlank(message = "calorie count cannot be blank")
    private Integer calorieCount;
    @NotBlank(message = "image url cannot be blank")
    private String imageUrl;
    @Min(value = 0, message = "meal price cannot be negative")
    @NotBlank(message = "meal price cannot be blank")
    private BigDecimal price;
}
