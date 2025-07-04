package fr.thomasdindin.barapp.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CategorieDto(Integer idCategorie,@NotNull String libelle) implements Serializable {
}