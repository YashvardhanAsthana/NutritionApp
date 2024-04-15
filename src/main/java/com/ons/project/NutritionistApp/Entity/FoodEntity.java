package com.ons.project.NutritionistApp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Setter
@Getter
@Table(name = "Foods")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="Name")
    private String name;

    @Column(name="NDB")
    private String ndb;

    @Column(name="Category")
    private String category;

    @Column(name="Water")
    private String water;

    @Column(name="Protein")
    private String protein;

    @Column(name="Carbohydrates")
    private String carbs;

    @Column(name="Fats")
    private String fats;


//
//    @ManyToMany(mappedBy = "favoriteFoods")
//    private Set<UserEntity> fans = new HashSet<>();

}
