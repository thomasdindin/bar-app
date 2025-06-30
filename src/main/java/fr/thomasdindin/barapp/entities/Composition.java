package fr.thomasdindin.barapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "composition")
public class Composition {
    @SequenceGenerator(name = "composition_id_gen", sequenceName = "cocktail_id_cocktail_seq", allocationSize = 1)
    @EmbeddedId
    private CompositionId id;

    @MapsId("idCocktail")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_cocktail", nullable = false)
    private Cocktail idCocktail;

    @MapsId("idIngredient")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_ingredient", nullable = false)
    private Ingredient idIngredient;

}