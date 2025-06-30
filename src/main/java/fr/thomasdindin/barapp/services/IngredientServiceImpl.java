package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.entities.Ingredient;
import fr.thomasdindin.barapp.repositories.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Ingredient"+ id + "not found"));
    }

    @Override
    public Ingredient updateIngredient(int id, Ingredient details) {
        Ingredient ingredient = getIngredientById(id);
        ingredient.setLibelle(details.getLibelle());
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(int id) {
        Ingredient ingredient = getIngredientById(id);
        ingredientRepository.delete(ingredient);
    }
}
