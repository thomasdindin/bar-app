package fr.thomasdindin.barapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_categorie", nullable = false)
    private Integer idCategorie;

    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;

    @OneToMany(
            mappedBy = "categorie",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private Set<Cocktail> cocktails = new LinkedHashSet<>();
}
