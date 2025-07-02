package fr.thomasdindin.barapp.mappers;

import fr.thomasdindin.barapp.dto.CommandeDto;
import fr.thomasdindin.barapp.dto.LigneCommandeDto;
import fr.thomasdindin.barapp.dto.UtilisateurDto;
import fr.thomasdindin.barapp.entities.Commande;
import fr.thomasdindin.barapp.entities.LigneCommande;
import fr.thomasdindin.barapp.entities.Utilisateur;

import java.util.Set;
import java.util.stream.Collectors;

public class CommandeMapper {
    public static CommandeDto toDto(Commande entity) {
        if (entity == null) return null;
        Utilisateur u = entity.getIdUtilisateur();
        UtilisateurDto userDto = null;
        if (u != null) {
            userDto = new UtilisateurDto(u.getId(), u.getNom(), u.getPrenom());
        }
        Set<LigneCommandeDto> lignes = entity.getLigneCommandes().stream()
                .map(LigneCommandeMapper::toDto)
                .collect(Collectors.toSet());
        return new CommandeDto(
                entity.getId(),
                entity.getDateCommande(),
                entity.getStatut(),
                userDto,
                lignes
        );
    }

    public static Commande toEntity(CommandeDto dto) {
        if (dto == null) return null;
        Commande entity = new Commande();
        entity.setId(dto.id());
        entity.setDateCommande(dto.dateCommande());
        entity.setStatut(dto.statut());
        if (dto.idUtilisateur() != null) {
            Utilisateur user = new Utilisateur();
            user.setId(dto.idUtilisateur().id());
            user.setNom(dto.idUtilisateur().nom());
            user.setPrenom(dto.idUtilisateur().prenom());
            entity.setIdUtilisateur(user);
        }
        if (dto.ligneCommandes() != null) {
            Set<LigneCommande> lignes = dto.ligneCommandes().stream()
                    .map(LigneCommandeMapper::toEntity)
                    .peek(l -> l.setIdCommande(entity))
                    .collect(Collectors.toSet());
            entity.setLigneCommandes(lignes);
        }
        return entity;
    }
}
