package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.entities.Utilisateur;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Utilisateur}
 */
@Value
public class UtilisateurDto implements Serializable {
    Integer id;
    String nom;
    String prenom;
}