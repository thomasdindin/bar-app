package fr.thomasdindin.barapp.mappers;

import fr.thomasdindin.barapp.dto.LigneCommandeDto;
import fr.thomasdindin.barapp.entities.LigneCommande;

public class LigneCommandeMapper {
    public static LigneCommandeDto toDto(LigneCommande entity) {
        if (entity == null) return null;
        return new LigneCommandeDto(
                entity.getId(),
                entity.getQte(),
                entity.getStatut(),
                VarianteMapper.toDto(entity.getIdVariante())
        );
    }

    public static LigneCommande toEntity(LigneCommandeDto dto) {
        if (dto == null) return null;
        LigneCommande entity = new LigneCommande();
        entity.setId(dto.id());
        entity.setQte(dto.qte());
        entity.setStatut(dto.statut());
        entity.setIdVariante(VarianteMapper.toEntity(dto.idVariante(), null));
        return entity;
    }
}
