package com.ons.project.NutritionistApp.Service.Impl;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.DTO.UserDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Entity.UserEntity;
import com.ons.project.NutritionistApp.Repository.FoodRepository;
import com.ons.project.NutritionistApp.Repository.UserRepository;
import com.ons.project.NutritionistApp.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public UserDTO getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userEntity.getId());
            userDTO.setUsername(userEntity.getUsername());
            return userDTO;
        }
        return null;
    }

    @Override
    public UserEntity createUser(UserDTO userDTO) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());

        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public UserEntity updateUser(Long userId, UserDTO userDTO) {

        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());
        UserEntity updatedUser = userRepository.save(existingUser);

        return existingUser;
    }

    @Override
    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "User data deleted";
    }

    @Override
    @Transactional
    public void addBookmark(Long userId, Long foodId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        FoodEntity food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        user.getBookmarks().add(food);
        userRepository.save(user);
    }

    @Override
    public Set<FoodDTO> getBookmarks(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Set<FoodDTO> bookmarkDTOs = new HashSet<>();
        for (FoodEntity food : user.getBookmarks()) {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setName(food.getName());
            foodDTO.setNdb(food.getNdb());

        }
        return bookmarkDTOs;
    }

    @Override
    public void removeBookmark(Long userId, Long foodId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoodEntity foodToRemove = null;
        for (FoodEntity food : user.getBookmarks()) {
            if (food.getId().equals(foodId)) {
                foodToRemove = food;
                break;
            }
        }

        if (foodToRemove != null) {
            user.getBookmarks().remove(foodToRemove);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Bookmark not found for user with ID: " + userId + " and food ID: " + foodId);
        }
    }

}
