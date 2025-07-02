package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Integer> {
}
