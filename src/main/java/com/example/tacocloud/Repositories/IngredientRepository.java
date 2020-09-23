package com.example.tacocloud.Repositories;

import com.example.tacocloud.Models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
