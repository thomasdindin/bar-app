package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.entities.Cocktail;
import fr.thomasdindin.barapp.repositories.CocktailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CocktailServiceImpl implements CocktailService {
    private final CocktailRepository cocktailRepository;

    @Override
    public Cocktail createCocktail(Cocktail cocktail) {
        return cocktailRepository.save(cocktail);
    }

    @Override
    public List<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }

    @Override
    public Cocktail getCocktailById(int id) {
        return cocktailRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Cocktail"+ id + "not found"));
    }

    @Override
    public List<Cocktail> getCocktailsByCategorie(String categorie) {
        List<Cocktail> cocktails = cocktailRepository.findCocktailsByCategorie(categorie);
        if (cocktails.isEmpty()) {
            throw new ResourceAccessException("No cocktails found in category: " + categorie);
        }
        return cocktails;
    }

    @Override
    public Cocktail updateCocktail(int id, Cocktail details) {
        Cocktail cocktail = getCocktailById(id);
        cocktail.setLibelle(details.getLibelle());
        cocktail.setDescription(details.getDescription());
        cocktail.setCategorie(details.getCategorie());
        cocktail.setImage(details.getImage());
        return cocktailRepository.save(cocktail);
    }

    @Override
    public void deleteCocktail(int id) {
        Cocktail cocktail = getCocktailById(id);
        cocktailRepository.delete(cocktail);
    }
}
