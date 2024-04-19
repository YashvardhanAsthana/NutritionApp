package com.ons.project.NutritionistApp.Service.Impl;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.DTO.UserDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Entity.UserEntity;
import com.ons.project.NutritionistApp.Repository.FoodRepository;
import com.ons.project.NutritionistApp.Repository.UserRepository;
import com.ons.project.NutritionistApp.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public FoodEntity addFood(FoodDTO foodDTO){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName(foodDTO.getName());
        foodEntity.setCarbs(foodDTO.getCarbs());
        foodEntity.setNdb(foodDTO.getNdb());
        foodEntity.setFats(foodDTO.getFats());
        foodEntity.setCategory(foodDTO.getCategory());
        foodEntity.setProtein(foodDTO.getProtein());
        foodEntity.setWater(foodDTO.getWater());

        FoodEntity savedFood = foodRepository.save(foodEntity);
        return foodEntity;
    }
    @Override
    public FoodDTO getFoodById(Long id) {
        FoodEntity foodEntity = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(foodEntity.getName());
        foodDTO.setCarbs(foodEntity.getCarbs());
        foodDTO.setNdb(foodEntity.getNdb());
        foodDTO.setProtein(foodEntity.getProtein());
        foodDTO.setFats(foodEntity.getFats());
        foodDTO.setWater(foodEntity.getWater());
        foodDTO.setCategory(foodEntity.getCategory());

        return foodDTO;
    }

    @Override
    public FoodEntity updateFood(Long id, FoodDTO foodDTO) {
        Optional<FoodEntity> optionalFood = foodRepository.findById(id);

        if (optionalFood.isPresent()) {
            FoodEntity existingFood = optionalFood.get();
            existingFood.setName(foodDTO.getName());
            existingFood.setCarbs(foodDTO.getCarbs());
            existingFood.setNdb(foodDTO.getNdb());
            existingFood.setFats(foodDTO.getFats());
            existingFood.setCategory(foodDTO.getCategory());
            existingFood.setProtein(foodDTO.getProtein());
            existingFood.setWater(foodDTO.getWater());

            return foodRepository.save(existingFood);
        }

        throw new RuntimeException("Food not found for ID: " + id);
    }


    @Override
    public List<FoodDTO> getAllFoods() {
        List<FoodEntity> foodEntities = foodRepository.findAll();
        List<FoodDTO> foodDTOs = new ArrayList<>();

        for (FoodEntity foodEntity : foodEntities) {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setName(foodEntity.getName());
            foodDTO.setCarbs(foodEntity.getCarbs());
            foodDTO.setNdb(foodEntity.getNdb());
            foodDTO.setFats(foodEntity.getFats());
            foodDTO.setCategory(foodEntity.getCategory());
            foodDTO.setProtein(foodEntity.getProtein());
            foodDTO.setWater(foodEntity.getWater());

            foodDTOs.add(foodDTO);
        }
        return foodDTOs;
    }
    @Override
    public boolean deleteFoodById(Long id) {
        Optional<FoodEntity> optionalFood = foodRepository.findById(id);
        if (optionalFood.isPresent()) {
            foodRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
