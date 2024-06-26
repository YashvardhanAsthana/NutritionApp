package com.ons.project.NutritionistApp.Controller;

import com.ons.project.NutritionistApp.DTO.FoodDTO;
import com.ons.project.NutritionistApp.DTO.UserDTO;
import com.ons.project.NutritionistApp.Entity.UserEntity;
import com.ons.project.NutritionistApp.Login.LoginRequest;
import com.ons.project.NutritionistApp.Login.LoginResponse;
import com.ons.project.NutritionistApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{userId}")
    public UserEntity updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            return ResponseEntity.ok("User with ID " + userId + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found");
        }
    }

    @PostMapping("/{userId}/bookmark/{foodId}")
    public ResponseEntity<Void> addBookmark(@PathVariable Long userId, @PathVariable Long foodId) {
        userService.addBookmark(userId, foodId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/bookmarks")
    public ResponseEntity<Object> getUserBookmarksWithFoodDetails(@PathVariable Long userId) {
        String bookmarksInfo = userService.getUserBookmarksWithFoodDetails(userId);
        if (bookmarksInfo.startsWith("User not found.")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookmarksInfo);
        } else if (bookmarksInfo.startsWith("User has no bookmarks.")) {
            return ResponseEntity.status(HttpStatus.OK).body(bookmarksInfo);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(bookmarksInfo);
        }
    }

    @DeleteMapping("/{userId}/bookmark/{foodId}")
    public ResponseEntity<Void> removeBookmark(@PathVariable Long userId, @PathVariable Long foodId) {
        userService.removeBookmark(userId, foodId);
        return ResponseEntity.ok().build();
    }

}
