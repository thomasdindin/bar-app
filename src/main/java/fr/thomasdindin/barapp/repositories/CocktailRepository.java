package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
    List<Cocktail> findCocktailsByCategorie(String categorie);
}