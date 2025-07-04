package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.dto.IngredientDto;
import fr.thomasdindin.barapp.entities.Ingredient;
import fr.thomasdindin.barapp.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<IngredientDto> createIngredient(@RequestBody IngredientDto ingredient) {
        return ResponseEntity.ok(ingredientService.createIngredient(ingredient));
    }

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable int id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PutMapping
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<IngredientDto> updateIngredient(@RequestBody IngredientDto ingredient) {
        return ResponseEntity.ok(ingredientService.updateIngredient(ingredient));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
