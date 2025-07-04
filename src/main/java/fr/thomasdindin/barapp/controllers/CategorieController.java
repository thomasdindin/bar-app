package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.dto.CategorieDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.services.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<CategorieDto> createCategory(@RequestBody CategorieDto categorie) {
        return ResponseEntity.ok(categorieService.createCategory(categorie));
    }

    @PutMapping
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<CategorieDto> updateCategory(@RequestBody CategorieDto categorie) {
        return ResponseEntity.ok(categorieService.updateCategory(categorie));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") int id) {
        categorieService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
