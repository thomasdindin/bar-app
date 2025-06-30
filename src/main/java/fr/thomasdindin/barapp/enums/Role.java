package fr.thomasdindin.barapp.enums;

public enum Role {
    CLIENT(0, "Client"),
    BARMAKER(1, "Barmaker");

    private final int id;
    private final String libelle;
    Role(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
}
