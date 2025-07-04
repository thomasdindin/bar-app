package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull@Email
    private String email;
    @NotNull
    private String password;
    private Role role;
}
