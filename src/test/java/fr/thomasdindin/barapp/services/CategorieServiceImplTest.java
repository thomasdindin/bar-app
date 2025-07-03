package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.CategorieDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.repositories.CategorieRepository;
import fr.thomasdindin.barapp.services.CategorieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategorieServiceImplTest {

    @Mock
    private CategorieRepository repository;

    @InjectMocks
    private CategorieServiceImpl service;

    private Categorie entity;

    @BeforeEach
    void setup() {
        entity = new Categorie();
        entity.setIdCategorie(1);
        entity.setLibelle("Cocktails");
    }

    @Test
    void createCategory_shouldSaveAndReturnDto() {
        when(repository.save(any(Categorie.class))).thenReturn(entity);

        CategorieDto dto = new CategorieDto(null, "Cocktails");
        CategorieDto result = service.createCategory(dto);

        assertThat(result.idCategorie()).isEqualTo(1);
        assertThat(result.libelle()).isEqualTo("Cocktails");
        verify(repository).save(any(Categorie.class));
    }

    @Test
    void getAllCategories_returnsMappedDtos() {
        when(repository.findAll()).thenReturn(List.of(entity));

        List<CategorieDto> list = service.getAllCategories();

        assertThat(list).hasSize(1);
        assertThat(list.get(0).libelle()).isEqualTo("Cocktails");
    }

    @Test
    void getCategoryById_whenFound() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));

        CategorieDto dto = service.getCategoryById(1);

        assertThat(dto.idCategorie()).isEqualTo(1);
    }

    @Test
    void getCategoryById_whenMissing_throws() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getCategoryById(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateCategory_shouldSave() {
        when(repository.save(any(Categorie.class))).thenReturn(entity);
        CategorieDto dto = new CategorieDto(1, "Cocktails");

        CategorieDto res = service.updateCategory(dto);

        assertThat(res.idCategorie()).isEqualTo(1);
        verify(repository).save(any(Categorie.class));
    }

    @Test
    void deleteCategory_callsRepository() {
        service.deleteCategory(42);
        verify(repository).deleteById(42);
    }
}
