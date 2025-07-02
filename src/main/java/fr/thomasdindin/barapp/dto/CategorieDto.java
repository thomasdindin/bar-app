package fr.thomasdindin.barapp.dto;

import java.io.Serializable;

public record CategorieDto(Integer idCategorie, String libelle) implements Serializable {
}