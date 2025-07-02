import fr.thomasdindin.barapp.dto.CategorieDto;
import fr.thomasdindin.barapp.dto.CocktailDto;
import fr.thomasdindin.barapp.dto.VarianteDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.entities.Cocktail;
import fr.thomasdindin.barapp.entities.Variante;
import fr.thomasdindin.barapp.repositories.CocktailRepository;
import fr.thomasdindin.barapp.repositories.VarianteRepository;
import fr.thomasdindin.barapp.services.CocktailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CocktailServiceImplTest {

    @Mock
    private CocktailRepository cocktailRepository;
    @Mock
    private VarianteRepository varianteRepository;
    @InjectMocks
    private CocktailServiceImpl service;

    private CocktailDto inputDto;
    private Cocktail savedEntity;

    @BeforeEach
    void setup() {
        VarianteDto varianteDto = new VarianteDto(null, "M", BigDecimal.ONE, 0);
        inputDto = new CocktailDto(null, "Mojito", "desc", Collections.emptySet(), Set.of(varianteDto), new CategorieDto(1, "cat"));
        savedEntity = new Cocktail();
        savedEntity.setId(1);
        savedEntity.setLibelle("Mojito");
        savedEntity.setDescription("desc");
        Categorie cat = new Categorie();
        cat.setIdCategorie(1);
        cat.setLibelle("cat");
        savedEntity.setCategorie(cat);
        savedEntity.setVariantes(new LinkedHashSet<>());
    }

    @Test
    void createCocktail_persistsVariantsAndReturnsDto() {
        when(cocktailRepository.save(any(Cocktail.class))).thenReturn(savedEntity);

        CocktailDto result = service.createCocktail(inputDto);

        verify(cocktailRepository).save(any(Cocktail.class));
        verify(varianteRepository).saveAll(anySet());
        assertThat(result.id()).isEqualTo(1);
    }

    @Test
    void getCocktailsGroupedByCategorie_groupsResults() {
        Cocktail c1 = new Cocktail();
        c1.setId(1);
        c1.setLibelle("c1");
        Categorie cat1 = new Categorie();
        cat1.setIdCategorie(1);
        cat1.setLibelle("A");
        c1.setCategorie(cat1);
        Cocktail c2 = new Cocktail();
        c2.setId(2);
        c2.setLibelle("c2");
        c2.setCategorie(cat1);
        when(cocktailRepository.findAll()).thenReturn(List.of(c1, c2));

        Map<String, List<CocktailDto>> map = service.getCocktailsGroupedByCategorie();

        assertThat(map).containsKey("A");
        assertThat(map.get("A")).hasSize(2);
    }

    @Test
    void deleteCocktail_whenMissing_throws() {
        when(cocktailRepository.existsById(1)).thenReturn(false);
        assertThatThrownBy(() -> service.deleteCocktail(1))
                .isInstanceOf(ResourceAccessException.class);
    }

    @Test
    void deleteCocktail_whenExists_deletes() {
        when(cocktailRepository.existsById(1)).thenReturn(true);
        Cocktail e = new Cocktail();
        when(cocktailRepository.findById(1)).thenReturn(Optional.of(e));
        service.deleteCocktail(1);
        verify(cocktailRepository).delete(e);
    }
}
