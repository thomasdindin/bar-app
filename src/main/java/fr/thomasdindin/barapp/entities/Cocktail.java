package fr.thomasdindin.barapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @ManyToMany
    @JoinTable(name = "composition",
            joinColumns = @JoinColumn(name = "id_cocktail"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
    private Set<Ingredient> ingredients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCocktail")
    private Set<Variante> variantes = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_categorie",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_cocktail_categorie")
    )
    @JsonIgnore                             // 1. on cache l’objet complet
    private Categorie categorie;

    /**
     * 2. On expose côté JSON la seule donnée « libellé » de la catégorie.
     *    Jackson appellera automatiquement ce getter.
     */
    @JsonProperty("categorie")
    public String getCategorieLibelle() {
        return categorie != null ? categorie.getLibelle() : null;
    }

}