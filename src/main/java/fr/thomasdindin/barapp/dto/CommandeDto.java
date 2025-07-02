package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.entities.Commande;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link Commande}
 */
@Value
public class CommandeDto implements Serializable {
    Integer id;
    Instant dateCommande;
    String statut;
    UtilisateurDto idUtilisateur;
    Set<LigneCommandeDto> ligneCommandes;
}