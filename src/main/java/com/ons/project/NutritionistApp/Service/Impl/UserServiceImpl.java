package com.ons.project.NutritionistApp.Service.Impl;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.DTO.UserDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Entity.UserEntity;
import com.ons.project.NutritionistApp.Repository.FoodRepository;
import com.ons.project.NutritionistApp.Repository.UserRepository;
import com.ons.project.NutritionistApp.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userEntity.getId());
            userDTO.setUsername(userEntity.getUsername());

            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public String login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {

            return generateToken(username);
        }
        return null;
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, "yourSecretKey") // Use a strong secret key here
                .compact();
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
    public boolean deleteUser(Long userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
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

    public String getUserBookmarksWithFoodDetails(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            Set<FoodEntity> bookmarks = user.getBookmarks();
            if (bookmarks.isEmpty()) {
                return "User has no bookmarks.";
            }
            StringBuilder result = new StringBuilder();
            for (FoodEntity food : bookmarks) {
                result.append("Food ID: ").append(food.getId()).append(", Name: ").append(food.getName()).append("\n");
            }
            return result.toString();
        } else {
            return "User not found.";
        }
    }

}
