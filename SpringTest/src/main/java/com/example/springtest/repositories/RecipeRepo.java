package com.example.springtest.repositories;

import com.example.springtest.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    List<Recipe> findByNameContaining(String name);
}