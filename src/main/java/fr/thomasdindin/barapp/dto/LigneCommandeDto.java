package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.entities.LigneCommande;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link LigneCommande}
 */
@Value
public class LigneCommandeDto implements Serializable {
    Integer id;
    Integer qte;
    String statut;
    VarianteDto idVariante;
}