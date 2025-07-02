package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}