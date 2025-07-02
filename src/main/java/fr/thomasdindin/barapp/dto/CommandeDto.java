package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.entities.Commande;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public record CommandeDto(Integer id, Instant dateCommande, String statut, Integer idUtilisateur,
                          Set<LigneCommandeDto> ligneCommandes) implements Serializable {
}