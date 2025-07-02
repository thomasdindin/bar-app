package fr.thomasdindin.barapp.mappers;

import fr.thomasdindin.barapp.dto.VarianteDto;
import fr.thomasdindin.barapp.entities.Cocktail;
import fr.thomasdindin.barapp.entities.Variante;

public class VarianteMapper {
    public static VarianteDto toDto(Variante entity) {
        if (entity == null) return null;
        return new VarianteDto(
                entity.getId(),
                entity.getTaille(),
                entity.getPrix(),
                entity.getIdCocktail()
        );
    }

    public static Variante toEntity(VarianteDto dto, Cocktail parentCocktail) {
        if (dto == null) return null;
        Variante entity = new Variante();
        entity.setId(dto.id());
        entity.setTaille(dto.taille());
        entity.setPrix(dto.prix());
        entity.setIdCocktail(parentCocktail.getId());
        return entity;
    }
}
