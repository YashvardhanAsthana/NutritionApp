package com.ons.project.NutritionistApp.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="Name")
    private String name;

    @Column(name="Description")
    private String description;

    @ManyToMany(mappedBy = "favoriteFoods")
    private Set<UserEntity> fans = new HashSet<>();

}
