package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}