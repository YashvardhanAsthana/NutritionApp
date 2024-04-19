package com.ons.project.NutritionistApp.Service;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.DTO.UserDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDTO getUserByUsername(String username);

    List<UserDTO> getAllUsers();

    UserEntity createUser(UserDTO userDTO);

    UserEntity updateUser(Long userId, UserDTO userDTO);

    boolean deleteUser(Long userId);

    void addBookmark(Long userId, Long foodId);
    Set<FoodDTO> getBookmarks(Long userId);

    void removeBookmark(Long userId, Long foodId);

    String login(String username, String password);

    String getUserBookmarksWithFoodDetails(Long userId);

}
