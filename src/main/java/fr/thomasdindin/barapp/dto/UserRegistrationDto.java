package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
}
