package com.ons.project.NutritionistApp.Repository;

import com.ons.project.NutritionistApp.Entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
}
