package fr.thomasdindin.barapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_commande", nullable = false)
    private Integer id;

    @ColumnDefault("now()")
    @Column(name = "date_commande", nullable = false)
    private Instant dateCommande;

    @Column(name = "statut", nullable = false, length = 50)
    private String statut;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur idUtilisateur;

    @OneToMany(mappedBy = "idCommande")
    private Set<LigneCommande> ligneCommandes = new LinkedHashSet<>();


}
