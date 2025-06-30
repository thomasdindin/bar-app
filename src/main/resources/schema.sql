CREATE TABLE IF NOT EXISTS utilisateur (
                             id_utilisateur   SERIAL PRIMARY KEY,
                             nom               VARCHAR(50)   NOT NULL,
                             prenom            VARCHAR(50)   NOT NULL,
                             date_inscription  TIMESTAMP     NOT NULL DEFAULT now(),
                             email             VARCHAR(100)  NOT NULL UNIQUE,
                             mdp               VARCHAR(255)  NOT NULL,
                             role              VARCHAR(20)   NOT NULL
);

CREATE TABLE IF NOT EXISTS cocktail (
                          id_cocktail SERIAL    PRIMARY KEY,
                          libelle     VARCHAR(100) NOT NULL,
                          description TEXT,
                          categorie   VARCHAR(50),
                          image       VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ingredients (
                             id_ingredient SERIAL   PRIMARY KEY,
                             libelle       VARCHAR(100) NOT NULL
);

-- table d’association many-to-many Cocktails ↔ Ingrédients
CREATE TABLE IF NOT EXISTS composition (
                             id_cocktail   INT NOT NULL REFERENCES cocktail(id_cocktail) ON DELETE CASCADE,
                             id_ingredient INT NOT NULL REFERENCES ingredients(id_ingredient) ON DELETE CASCADE,
                             PRIMARY KEY (id_cocktail, id_ingredient)
);

CREATE TABLE IF NOT EXISTS variantes (
                           id_variante SERIAL PRIMARY KEY,
                           taille       CHAR(1) NOT NULL CHECK (taille IN ('S','M','L')),
                           prix         NUMERIC(6,2) NOT NULL,
                           id_cocktail  INT NOT NULL REFERENCES cocktail(id_cocktail) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS commande (
                          id_commande      SERIAL PRIMARY KEY,
                          date_commande    TIMESTAMP    NOT NULL DEFAULT now(),
                          statut           VARCHAR(20)  NOT NULL,
                          id_utilisateur   INT NOT NULL REFERENCES utilisateur(id_utilisateur)
);

CREATE TABLE IF NOT EXISTS ligne_commande (
                                id_ligne      SERIAL PRIMARY KEY,
                                qte           INT           NOT NULL CHECK (qte > 0),
                                statut        VARCHAR(20)   NOT NULL,
                                taille        CHAR(1)       NOT NULL,
                                id_variante   INT NOT NULL REFERENCES variantes(id_variante),
                                id_commande   INT NOT NULL REFERENCES commande(id_commande)
);