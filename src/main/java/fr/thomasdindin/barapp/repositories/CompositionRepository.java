package fr.thomasdindin.barapp.repositories;

import fr.thomasdindin.barapp.entities.Composition;
import fr.thomasdindin.barapp.entities.CompositionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionRepository extends JpaRepository<Composition, CompositionId> {
}