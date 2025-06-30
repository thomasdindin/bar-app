package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}