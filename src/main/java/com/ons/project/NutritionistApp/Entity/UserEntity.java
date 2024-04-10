package com.ons.project.NutritionistApp.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_foods",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<FoodEntity> favoriteFoods = new HashSet<>();

    public void addFavoriteFood(FoodEntity food) {
        favoriteFoods.add(food);
        food.getFans().add(this);
    }

    public void removeFavoriteFood(FoodEntity food) {
        favoriteFoods.remove(food);
        food.getFans().remove(this);
    }
}
