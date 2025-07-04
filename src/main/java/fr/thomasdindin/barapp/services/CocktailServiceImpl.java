package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.CocktailDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.entities.Cocktail;
import fr.thomasdindin.barapp.entities.Variante;
import fr.thomasdindin.barapp.mappers.CocktailMapper;
import fr.thomasdindin.barapp.repositories.CocktailRepository;
import fr.thomasdindin.barapp.repositories.VarianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CocktailServiceImpl implements CocktailService {
    private final CocktailRepository cocktailRepository;
    private final VarianteRepository varianteRepository;

    @Override
    @Transactional
    public CocktailDto createCocktail(CocktailDto cocktail) {
        Cocktail entity = CocktailMapper.toEntity(cocktail);
        // Extract variants and clear to get generated ID first
        java.util.Set<Variante> variants = new java.util.LinkedHashSet<>(entity.getVariantes());
        entity.getVariantes().clear();
        Cocktail saved = cocktailRepository.save(entity);
        variants.forEach(v -> v.setIdCocktail(saved.getId()));
        varianteRepository.saveAll(variants);
        saved.setVariantes(variants);
        return CocktailMapper.toDto(saved);
    }

    @Override
    public List<CocktailDto> getAllCocktails() {
        return cocktailRepository.findAll().stream().map(CocktailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CocktailDto getCocktailById(int id) {
        return CocktailMapper.toDto(cocktailRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Cocktail"+ id + "not found")));
    }

    @Override
    public List<CocktailDto> getCocktailsByCategorie(String libelleCategorie) {
        return cocktailRepository.findByCategorie_Libelle(libelleCategorie).stream().map(CocktailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CocktailDto updateCocktail(CocktailDto details) {
        Cocktail entity = CocktailMapper.toEntity(details);
        java.util.Set<Variante> variants = new java.util.LinkedHashSet<>(entity.getVariantes());
        entity.getVariantes().clear();
        Cocktail saved = cocktailRepository.save(entity);
        // remove existing variants linked to this cocktail
        varianteRepository.deleteAll(varianteRepository.findByIdCocktail(saved.getId()));
        variants.forEach(v -> v.setIdCocktail(saved.getId()));
        varianteRepository.saveAll(variants);
        saved.setVariantes(variants);
        return CocktailMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteCocktail(int id) {
        if(!cocktailRepository.existsById(id)) {
            throw new ResourceAccessException("Cocktail with id " + id + " not found");
        } else {
            cocktailRepository.delete(cocktailRepository.findById(id).get());
        }
    }

    @Override
    public Map<String, List<CocktailDto>> getCocktailsGroupedByCategorie() {
        List<Cocktail> all = cocktailRepository.findAll();
        return all.stream()
                .collect(Collectors.groupingBy(cocktail -> cocktail.getCategorie().getLibelle(),
                        Collectors.mapping(CocktailMapper::toDto, Collectors.toList())));
    }

}
