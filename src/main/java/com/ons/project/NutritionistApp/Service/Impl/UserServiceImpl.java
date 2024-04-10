package com.ons.project.NutritionistApp.Service.Impl;

import com.ons.project.NutritionistApp.DTO.UserDTO;
import com.ons.project.NutritionistApp.Entity.FoodEntity;
import com.ons.project.NutritionistApp.Entity.UserEntity;
import com.ons.project.NutritionistApp.Repository.UserRepository;
import com.ons.project.NutritionistApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
    public UserEntity addFavoriteFood(Long userId, FoodEntity food) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.addFavoriteFood(food);
            return userRepository.save(user);
        }

        return null;
    }
}
