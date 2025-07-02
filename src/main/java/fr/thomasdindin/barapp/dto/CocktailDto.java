package fr.thomasdindin.barapp.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;


public record CocktailDto(Integer id, String libelle, String description, Set<IngredientDto> ingredients,
                          Set<VarianteDto> variantes, CategorieDto categorie) implements Serializable {
}