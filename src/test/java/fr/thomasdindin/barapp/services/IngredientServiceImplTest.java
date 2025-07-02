import fr.thomasdindin.barapp.dto.IngredientDto;
import fr.thomasdindin.barapp.entities.Ingredient;
import fr.thomasdindin.barapp.repositories.IngredientRepository;
import fr.thomasdindin.barapp.services.IngredientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    private IngredientRepository repository;

    @InjectMocks
    private IngredientServiceImpl service;

    private Ingredient entity;

    @BeforeEach
    void setup() {
        entity = new Ingredient();
        entity.setId(1);
        entity.setLibelle("Citron");
    }

    @Test
    void createIngredient_savesAndReturnsDto() {
        when(repository.save(any(Ingredient.class))).thenReturn(entity);
        IngredientDto dto = new IngredientDto(null, "Citron");

        IngredientDto res = service.createIngredient(dto);

        assertThat(res.id()).isEqualTo(1);
        assertThat(res.libelle()).isEqualTo("Citron");
    }

    @Test
    void getAllIngredients_returnsList() {
        when(repository.findAll()).thenReturn(List.of(entity));
        List<IngredientDto> list = service.getAllIngredients();
        assertThat(list).hasSize(1);
    }

    @Test
    void getIngredientById_whenFound() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        IngredientDto dto = service.getIngredientById(1);
        assertThat(dto.id()).isEqualTo(1);
    }

    @Test
    void getIngredientById_whenMissing_throws() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getIngredientById(1))
                .isInstanceOf(ResourceAccessException.class);
    }

    @Test
    void updateIngredient_callsSave() {
        when(repository.save(any(Ingredient.class))).thenReturn(entity);
        IngredientDto dto = new IngredientDto(1, "Citron");
        IngredientDto res = service.updateIngredient(dto);
        assertThat(res.id()).isEqualTo(1);
        verify(repository).save(any(Ingredient.class));
    }

    @Test
    void deleteIngredient_whenFound() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        service.deleteIngredient(1);
        verify(repository).delete(entity);
    }

    @Test
    void deleteIngredient_whenMissing_throws() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.deleteIngredient(1))
                .isInstanceOf(ResourceAccessException.class);
    }
}
