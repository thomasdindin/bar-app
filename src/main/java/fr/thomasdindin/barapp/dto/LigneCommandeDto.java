package fr.thomasdindin.barapp.dto;

import fr.thomasdindin.barapp.entities.LigneCommande;
import lombok.Value;

import java.io.Serializable;

public record LigneCommandeDto(Integer id, Integer qte, String statut, VarianteDto idVariante) implements Serializable {
}