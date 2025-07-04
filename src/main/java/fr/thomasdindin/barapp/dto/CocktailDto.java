package fr.thomasdindin.barapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;


public record CocktailDto(Integer id, @NotNull String libelle, String description, Set<IngredientDto> ingredients,
                          Set<VarianteDto> variantes,@NotNull CategorieDto categorie) implements Serializable {
}