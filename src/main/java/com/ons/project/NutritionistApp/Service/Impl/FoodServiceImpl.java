package com.ons.project.NutritionistApp.Service.Impl;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
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
    public List<FoodDTO> getAllFoods() {
        List<FoodEntity> foodEntities = foodRepository.findAll();
        List<FoodDTO> foodDTOs = new ArrayList<>();
        FoodDTO foodDTO = new FoodDTO();
        for (FoodEntity foodEntity : foodEntities) {
            foodDTO.setId(foodEntity.getId());
            foodDTO.setName(foodEntity.getName());
            foodDTO.setDescription(foodEntity.getDescription());
            foodDTOs.add(foodDTO);
        }
        return foodDTOs;
    }

    @Override
    public FoodEntity addFavoriteFood(Long userId, Long foodId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        Optional<FoodEntity> optionalFood = foodRepository.findById(foodId);

        if (optionalUser.isPresent() && optionalFood.isPresent()) {
            UserEntity user = optionalUser.get();
            FoodEntity food = optionalFood.get();

            user.addFavoriteFood(food);
            userRepository.save(user);

            return food;
        }

        return null;
    }

}
