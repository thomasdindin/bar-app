package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.CocktailDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.entities.Cocktail;

import java.util.List;
import java.util.Map;

public interface CocktailService {

    CocktailDto createCocktail(CocktailDto cocktail);

    List<CocktailDto> getAllCocktails();
    List<CocktailDto> getCocktailsByCategorie(String categorie);

    CocktailDto getCocktailById(int id);

    CocktailDto updateCocktail(CocktailDto cocktail);

    void deleteCocktail(int id);

    /**
     * Retourne tous les cocktails groupés par catégorie.
     */
    Map<String, List<CocktailDto>> getCocktailsGroupedByCategorie();
}