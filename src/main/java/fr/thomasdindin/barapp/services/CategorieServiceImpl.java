package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.CategorieDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.mappers.CategorieMapper;
import fr.thomasdindin.barapp.repositories.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService{

    private final CategorieRepository categorieRepository;

    @Override
    @Transactional
    public void deleteCategory(int id) {
        categorieRepository.deleteById(id);
    }

    @Override
    @Transactional

    public CategorieDto createCategory(CategorieDto categorie) {
        return CategorieMapper.toDto(categorieRepository.save(CategorieMapper.toEntity(categorie)));
    }

    @Override
    @Transactional

    public CategorieDto updateCategory(CategorieDto categorie) {
        return CategorieMapper.toDto(categorieRepository.save(CategorieMapper.toEntity(categorie)));
    }

    @Override
    public CategorieDto getCategoryById(int id) {
        return CategorieMapper.toDto(categorieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category with id " + id + " not found")));
    }

    @Override
    public List<CategorieDto> getAllCategories() {
        return categorieRepository.findAll().stream().map(
                CategorieMapper::toDto
        ).toList();
    }

}
