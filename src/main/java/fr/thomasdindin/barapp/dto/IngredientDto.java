package fr.thomasdindin.barapp.dto;

import lombok.Value;

import java.io.Serializable;

public record IngredientDto(Integer id, String libelle) implements Serializable {
}