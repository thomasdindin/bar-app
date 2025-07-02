package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.entities.Utilisateur;
import lombok.Value;

import java.io.Serializable;

public record UtilisateurDto(Integer id, String nom, String prenom) implements Serializable {
}