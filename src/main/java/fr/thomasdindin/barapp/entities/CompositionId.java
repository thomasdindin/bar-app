package fr.thomasdindin.barapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CompositionId implements Serializable {
    private static final long serialVersionUID = 8101184866952175445L;
    @Column(name = "id_cocktail", nullable = false)
    private Integer idCocktail;

    @Column(name = "id_ingredient", nullable = false)
    private Integer idIngredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CompositionId entity = (CompositionId) o;
        return Objects.equals(this.idCocktail, entity.idCocktail) &&
                Objects.equals(this.idIngredient, entity.idIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCocktail, idIngredient);
    }

}