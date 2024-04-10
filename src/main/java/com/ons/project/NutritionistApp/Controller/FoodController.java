package com.ons.project.NutritionistApp.Controller;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public ResponseEntity<List<FoodDTO>> getAllFoods() {
        List<FoodDTO> foodDTOList = foodService.getAllFoods();
        return ResponseEntity.ok(foodDTOList);
    }

    @PostMapping("/favorites")
    public ResponseEntity<String> addFavoriteFood(@RequestParam Long userId, @RequestParam Long foodId) {
        foodService.addFavoriteFood(userId, foodId);
        return ResponseEntity.ok("Favorite food added successfully");
    }

}
