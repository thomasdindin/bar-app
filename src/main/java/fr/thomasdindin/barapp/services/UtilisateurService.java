package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.UserRegistrationDto;
import fr.thomasdindin.barapp.entities.Utilisateur;

public interface UtilisateurService {
    /**
     * Enregistre un nouvel utilisateur (inscription).
     */
    Utilisateur register(UserRegistrationDto dto);

    /**
     * Recherche un utilisateur par son email.
     */
    Utilisateur getByEmail(String email);
}
