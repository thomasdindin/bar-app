package fr.thomasdindin.barapp.mappers;

import fr.thomasdindin.barapp.dto.CategorieDto;
import fr.thomasdindin.barapp.dto.CocktailDto;
import fr.thomasdindin.barapp.dto.IngredientDto;
import fr.thomasdindin.barapp.dto.VarianteDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.entities.Cocktail;
import fr.thomasdindin.barapp.entities.Ingredient;
import fr.thomasdindin.barapp.entities.Variante;

import java.util.Set;
import java.util.stream.Collectors;

public class CocktailMapper {
    public static CocktailDto toDto(Cocktail entity) {
        if (entity == null) return null;

        Set<IngredientDto> ingredients = entity.getIngredients().stream()
                .map(IngredientMapper::toDto)
                .collect(Collectors.toSet());
        Set<VarianteDto> variantes = entity.getVariantes().stream()
                .map(VarianteMapper::toDto)
                .collect(Collectors.toSet());
        CategorieDto categorie = CategorieMapper.toDto(entity.getCategorie());
        return new CocktailDto(
                entity.getId(),
                entity.getLibelle(),
                entity.getDescription(),
                ingredients,
                variantes,
                categorie
        );
    }

    public static Cocktail toEntity(CocktailDto dto) {
        if (dto == null) return null;
        Cocktail entity = new Cocktail();
        entity.setId(dto.id());
        entity.setLibelle(dto.libelle());
        entity.setDescription(dto.description());
        // category must be fetched by service / repository
        Categorie cat = CategorieMapper.toEntity(dto.categorie());
        entity.setCategorie(cat);
        // ingredients set
        Set<Ingredient> ings = dto.ingredients().stream()
                .map(IngredientMapper::toEntity)
                .collect(Collectors.toSet());
        entity.setIngredients(ings);
        Set<Variante> vars = dto.variantes().stream()
                .map(vdto -> VarianteMapper.toEntity(vdto, entity))
                .collect(Collectors.toSet());
        entity.setVariantes(vars);
        return entity;
    }
}
