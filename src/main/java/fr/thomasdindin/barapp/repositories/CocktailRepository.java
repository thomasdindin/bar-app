package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
    /**
     * Trouve tous les cocktails dont la catégorie.libelle correspond.
     * Spring Data JPA interprète “Categorie_Libelle” comme root.categorie.libelle.
     */

    List<Cocktail> findByCategorie_Libelle(String libelle);
}