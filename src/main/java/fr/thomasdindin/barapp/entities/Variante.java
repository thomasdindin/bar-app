package fr.thomasdindin.barapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "variantes")
public class Variante {
    @Id
    @ColumnDefault("nextval('variante_id_variante_seq')")
    @Column(name = "id_variante", nullable = false)
    private Integer id;

    @Column(name = "taille", nullable = false, length = 50)
    private String taille;

    @Column(name = "prix", nullable = false, precision = 8, scale = 2)
    private BigDecimal prix;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_cocktail", nullable = false)
    private int idCocktail;
}