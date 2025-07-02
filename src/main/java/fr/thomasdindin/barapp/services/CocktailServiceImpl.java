package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.CocktailDto;
import fr.thomasdindin.barapp.entities.Categorie;
import fr.thomasdindin.barapp.entities.Cocktail;
import fr.thomasdindin.barapp.mappers.CocktailMapper;
import fr.thomasdindin.barapp.repositories.CocktailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CocktailServiceImpl implements CocktailService {
    private final CocktailRepository cocktailRepository;

    @Override
    public CocktailDto createCocktail(CocktailDto cocktail) {
        return CocktailMapper.toDto(cocktailRepository.save(CocktailMapper.toEntity(cocktail)));
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
    public CocktailDto updateCocktail(CocktailDto details) {
        return CocktailMapper.toDto(cocktailRepository.save(CocktailMapper.toEntity(details)));
    }

    @Override
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
