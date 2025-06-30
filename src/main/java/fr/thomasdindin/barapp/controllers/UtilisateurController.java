package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.dto.UserRegistrationDto;
import fr.thomasdindin.barapp.entities.Utilisateur;
import fr.thomasdindin.barapp.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService userService;
    /**
     * POST /api/users/register
     * Crée un nouvel utilisateur.
     */
    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(
            @Validated @RequestBody UserRegistrationDto dto) {
        Utilisateur created = userService.register(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }
}
