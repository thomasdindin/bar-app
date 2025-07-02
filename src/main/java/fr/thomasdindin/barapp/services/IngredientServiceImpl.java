package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.IngredientDto;
import fr.thomasdindin.barapp.entities.Ingredient;
import fr.thomasdindin.barapp.mappers.IngredientMapper;
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
    public IngredientDto createIngredient(IngredientDto ingredient) {
        return IngredientMapper.toDto(ingredientRepository.save(IngredientMapper.toEntity(ingredient)));
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(IngredientMapper::toDto)
                .toList();
    }

    @Override
    public IngredientDto getIngredientById(int id) {
        return IngredientMapper.toDto(ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Ingredient"+ id + "not found")));
    }

    @Override
    public IngredientDto updateIngredient(IngredientDto details) {
        return IngredientMapper.toDto(ingredientRepository.save(IngredientMapper.toEntity(details)));
    }

    @Override
    public void deleteIngredient(int id) {
        ingredientRepository.delete(ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Ingredient with id " + id + " not found")));
    }
}
