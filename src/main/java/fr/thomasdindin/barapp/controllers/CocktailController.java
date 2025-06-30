package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.entities.Cocktail;
import fr.thomasdindin.barapp.services.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @PostMapping
    public ResponseEntity<Cocktail> createCocktail(@RequestBody Cocktail cocktail) {
        Cocktail created = cocktailService.createCocktail(cocktail);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<List<Cocktail>> getAllCocktails() {
        return ResponseEntity.ok(cocktailService.getAllCocktails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cocktail> getCocktailById(@PathVariable int id) {
        return ResponseEntity.ok(cocktailService.getCocktailById(id));
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Cocktail>> getCocktailById(@PathVariable String categorie) {
        return ResponseEntity.ok(cocktailService.getCocktailsByCategorie(categorie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cocktail> updateCocktail(@PathVariable int id, @RequestBody Cocktail cocktail) {
        return ResponseEntity.ok(cocktailService.updateCocktail(id, cocktail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable int id) {
        cocktailService.deleteCocktail(id);
        return ResponseEntity.noContent().build();
    }
}
