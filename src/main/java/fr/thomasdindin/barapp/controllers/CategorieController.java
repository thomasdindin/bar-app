package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.dto.CategorieDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.services.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;
    @GetMapping
    public ResponseEntity<List<CategorieDto>> getCategories() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieDto> getCategoryById(@PathVariable("id") int id) {
        return ResponseEntity.ok(categorieService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<CategorieDto> createCategory(CategorieDto categorie) {
        return ResponseEntity.ok(categorieService.createCategory(categorie));
    }

    @PutMapping
    public ResponseEntity<CategorieDto> updateCategory(CategorieDto categorie) {
        return ResponseEntity.ok(categorieService.updateCategory(categorie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") int id) {
        categorieService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
