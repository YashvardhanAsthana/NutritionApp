package com.ons.project.NutritionistApp.Service;

import com.ons.project.NutritionistApp.DTO.UserDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Entity.UserEntity;

public interface UserService {

    UserDTO getUserByUsername(String username);

    UserEntity createUser(UserDTO userDTO);

    UserEntity updateUser(Long userId, UserDTO userDTO);

    String deleteUser(Long userId);

    UserEntity addFavoriteFood(Long userId, FoodEntity food);

}
