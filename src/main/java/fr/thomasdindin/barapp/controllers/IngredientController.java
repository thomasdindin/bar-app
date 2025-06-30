package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.entities.Ingredient;
import fr.thomasdindin.barapp.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.createIngredient(ingredient));
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable int id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
