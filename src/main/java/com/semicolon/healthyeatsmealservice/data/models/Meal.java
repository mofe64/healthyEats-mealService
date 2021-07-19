package com.semicolon.healthyeatsmealservice.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    @Id
    private String id;
    private String name;
    private Integer calorieCount;
    private String imageUrl;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;
}
