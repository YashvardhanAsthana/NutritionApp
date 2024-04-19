package com.ons.project.NutritionistApp.Controller;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public List<FoodDTO> getAllFoods() {
        List<FoodDTO> foodList = foodService.getAllFoods();
        return foodList;
    }

    @GetMapping("/{id}")
    public FoodDTO getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }


    @PostMapping
    public FoodEntity addFood(@RequestBody FoodDTO foodDTO){
        FoodEntity foodEntity= foodService.addFood(foodDTO);
        return foodEntity;
    }

    @PutMapping("/{id}")
    public FoodEntity updateFood(@PathVariable Long id, @RequestBody FoodDTO foodDTO) {
        FoodEntity updatedFood = foodService.updateFood(id, foodDTO);
        return updatedFood;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable Long id) {
        boolean deleted = foodService.deleteFoodById(id);
        if (deleted) {
            return ResponseEntity.ok("Food with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Food with ID " + id + " not found");
        }
    }

}
