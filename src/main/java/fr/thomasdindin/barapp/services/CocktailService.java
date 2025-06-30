package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.entities.Cocktail;

import java.util.List;

public interface CocktailService {

    Cocktail createCocktail(Cocktail cocktail);

    List<Cocktail> getAllCocktails();
    List<Cocktail> getCocktailsByCategorie(String categorie);

    Cocktail getCocktailById(int id);

    Cocktail updateCocktail(int id, Cocktail cocktail);

    void deleteCocktail(int id);
}