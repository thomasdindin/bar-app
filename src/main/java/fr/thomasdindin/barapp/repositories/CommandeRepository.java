package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    List<Commande> findByIdUtilisateur_Id(Integer idUtilisateur);
}