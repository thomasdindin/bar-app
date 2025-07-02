package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.CommandeDto;
import fr.thomasdindin.barapp.enums.StatutCommande;

import java.util.List;

public interface CommandeService {
    CommandeDto createCommande(CommandeDto commande);
    List<CommandeDto> getAllCommandes();
    CommandeDto getCommandeById(int id);
    CommandeDto updateLigneStatus(int idLigne, StatutCommande statut);
    List<CommandeDto> getCommandesByClientId(int idClient);
}
