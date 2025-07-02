package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.dto.CocktailDto;
import fr.thomasdindin.barapp.entities.Cocktail;
import fr.thomasdindin.barapp.services.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @PostMapping
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<CocktailDto> createCocktail(@RequestBody CocktailDto cocktail) {
        return ResponseEntity.ok(cocktailService.createCocktail(cocktail));
    }

    @GetMapping
    public ResponseEntity<List<CocktailDto>> getAllCocktails() {
        return ResponseEntity.ok(cocktailService.getAllCocktails());
    }
    /**
     * GET /api/cocktails/by-category
     * Renvoie un objet JSON dont chaque clé est une catégorie
     * et la valeur la liste des cocktails de cette catégorie.
     */
    @GetMapping("/by-category")
    public ResponseEntity<Map<String, List<CocktailDto>>> getByCategory() {
        Map<String, List<CocktailDto>> grouped =
                cocktailService.getCocktailsGroupedByCategorie();
        return ResponseEntity.ok(grouped);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CocktailDto> getCocktailById(@PathVariable int id) {
        return ResponseEntity.ok(cocktailService.getCocktailById(id));
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<CocktailDto>> getCocktailByCategorie(@PathVariable String categorie) {
        return ResponseEntity.ok(cocktailService.getCocktailsByCategorie(categorie));
    }

    @PutMapping
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<CocktailDto> updateCocktail(@RequestBody CocktailDto cocktail) {
        return ResponseEntity.ok(cocktailService.updateCocktail(cocktail));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<Void> deleteCocktail(@PathVariable int id) {
        cocktailService.deleteCocktail(id);
        return ResponseEntity.noContent().build();
    }
}
