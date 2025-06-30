package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.enums.Role;
import lombok.Getter;

@Getter
public class UserRegistrationDto {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
}
