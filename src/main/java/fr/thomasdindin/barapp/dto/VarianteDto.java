package fr.thomasdindin.barapp.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

public record VarianteDto(Integer id, String taille, BigDecimal prix, int idCocktail) implements Serializable {
}