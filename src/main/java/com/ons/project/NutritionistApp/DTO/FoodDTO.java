package com.ons.project.NutritionistApp.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {

    private String name;

    private String ndb;

    private String category;

    private String water;

    private String protein;

    private String carbs;

    private String fats;
}
