package fr.thomasdindin.barapp.entities;

import fr.thomasdindin.barapp.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_utilisateur", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @ColumnDefault("now()")
    @Column(name = "date_inscription")
    private Instant dateInscription;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mdp", nullable = false)
    private String mdp;

    @Column(name = "role", nullable = false, length = 50)
    private Role role;

    @OneToMany(mappedBy = "idUtilisateur")
    private Set<Commande> commandes = new LinkedHashSet<>();

    @PrePersist
    private void prePersist() {
        if (dateInscription == null) {
            dateInscription = Instant.now();
        }
        if (role == null) {
            role = Role.CLIENT; // Default role
        }
    }
}
