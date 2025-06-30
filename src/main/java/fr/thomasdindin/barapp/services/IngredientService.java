package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    Ingredient getIngredientById(int id);

    Ingredient updateIngredient(int id, Ingredient ingredient);

    void deleteIngredient(int id);
}
