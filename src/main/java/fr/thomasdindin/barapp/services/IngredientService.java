package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.IngredientDto;
import fr.thomasdindin.barapp.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    IngredientDto createIngredient(IngredientDto ingredient);

    List<IngredientDto> getAllIngredients();

    IngredientDto getIngredientById(int id);

    IngredientDto updateIngredient(IngredientDto ingredient);

    void deleteIngredient(int id);
}
