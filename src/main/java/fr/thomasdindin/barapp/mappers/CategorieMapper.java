package fr.thomasdindin.barapp.mappers;

import fr.thomasdindin.barapp.dto.CategorieDto;
import fr.thomasdindin.barapp.entities.Categorie;

public class CategorieMapper {
    public static CategorieDto toDto(Categorie entity) {
        if (entity == null) return null;
        return new CategorieDto(entity.getIdCategorie(), entity.getLibelle());
    }

    public static Categorie toEntity(CategorieDto dto) {
        if (dto == null) return null;
        Categorie entity = new Categorie();
        entity.setIdCategorie(dto.idCategorie());
        entity.setLibelle(dto.libelle());
        return entity;
    }
}
