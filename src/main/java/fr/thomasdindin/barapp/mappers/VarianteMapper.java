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
        if (dto.id() != null && dto.id() > 0) {
            entity.setId(dto.id());
        }
        entity.setTaille(dto.taille());
        entity.setPrix(dto.prix());
        int idCocktail = dto.idCocktail();
        if (parentCocktail != null && parentCocktail.getId() != null) {
            idCocktail = parentCocktail.getId();
        }
        entity.setIdCocktail(idCocktail);
        return entity;
    }
}
