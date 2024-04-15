package com.ons.project.NutritionistApp.Service;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.DTO.UserDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface FoodService {

    List<FoodDTO> getAllFoods();
//    FoodEntity addFavoriteFood(Long userId, Long foodId);

    FoodEntity addFood(FoodDTO foodDTO);

    FoodEntity updateFood(Long id, FoodDTO foodDTO);

    FoodDTO getFood(Long id);
}
