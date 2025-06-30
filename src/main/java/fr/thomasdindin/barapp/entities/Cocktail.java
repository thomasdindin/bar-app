package fr.thomasdindin.barapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cocktail")
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cocktail_id_gen")
    @SequenceGenerator(name = "cocktail_id_gen", sequenceName = "cocktail_id_cocktail_seq", allocationSize = 1)
    @Column(name = "id_cocktail", nullable = false)
    private Integer id;

    @Column(name = "libelle", nullable = false, length = 150)
    private String libelle;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "categorie", length = 100)
    private String categorie;

    @Column(name = "image")
    private String image;

    @ManyToMany
    @JoinTable(name = "composition",
            joinColumns = @JoinColumn(name = "id_cocktail"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
    private Set<Ingredient> ingredients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCocktail")
    private Set<Variante> variantes = new LinkedHashSet<>();

}