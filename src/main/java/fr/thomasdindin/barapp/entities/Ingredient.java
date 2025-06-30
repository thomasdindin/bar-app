package fr.thomasdindin.barapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_gen")
    @SequenceGenerator(name = "ingredient_id_gen", sequenceName = "ingredient_id_ingredient_seq", allocationSize = 1)
    @Column(name = "id_ingredient", nullable = false)
    private Integer id;

    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;

}