package fr.thomasdindin.barapp.enums;

public enum StatutCommande {
    EN_ATTENTE("En attente"),
    EN_PREPARATION("En cours de préparation"),
    TERMINEE("Terminée");

    private final String libelle;

    StatutCommande(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
