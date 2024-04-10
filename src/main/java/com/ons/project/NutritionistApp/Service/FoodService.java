package com.ons.project.NutritionistApp.Service;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;

import java.util.List;

public interface FoodService {

    List<FoodDTO> getAllFoods();
    FoodEntity addFavoriteFood(Long userId, Long foodId);
}
