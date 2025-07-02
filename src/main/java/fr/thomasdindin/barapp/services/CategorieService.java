package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.CategorieDto;
import fr.thomasdindin.barapp.entities.Categorie;

import java.util.List;

public interface CategorieService {
    List<CategorieDto> getAllCategories();
    CategorieDto getCategoryById(int id);
    CategorieDto createCategory(CategorieDto categorie);
    CategorieDto updateCategory(CategorieDto categorie);
    void deleteCategory(int id);
}
