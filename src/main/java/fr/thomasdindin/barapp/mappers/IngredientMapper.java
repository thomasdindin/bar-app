package fr.thomasdindin.barapp.mappers;

import fr.thomasdindin.barapp.dto.IngredientDto;
import fr.thomasdindin.barapp.entities.Ingredient;

public class IngredientMapper {
    public static IngredientDto toDto(Ingredient entity) {
        if (entity == null) return null;
        return new IngredientDto(entity.getId(), entity.getLibelle());
    }

    public static Ingredient toEntity(IngredientDto dto) {
        if (dto == null) return null;
        Ingredient entity = new Ingredient();
        entity.setId(dto.id());
        entity.setLibelle(dto.libelle());
        return entity;
    }
}
