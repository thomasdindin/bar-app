package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.Variante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VarianteRepository extends JpaRepository<Variante, Integer> {
    List<Variante> findByIdCocktail(int idCocktail);
}
