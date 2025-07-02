package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.CommandeDto;
import fr.thomasdindin.barapp.enums.StatutCommande;
import fr.thomasdindin.barapp.entities.Commande;
import fr.thomasdindin.barapp.entities.LigneCommande;
import fr.thomasdindin.barapp.entities.Utilisateur;
import fr.thomasdindin.barapp.entities.Variante;
import fr.thomasdindin.barapp.mappers.CommandeMapper;
import fr.thomasdindin.barapp.repositories.CommandeRepository;
import fr.thomasdindin.barapp.repositories.LigneCommandeRepository;
import fr.thomasdindin.barapp.repositories.UtilisateurRepository;
import fr.thomasdindin.barapp.repositories.VarianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final VarianteRepository varianteRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public CommandeDto createCommande(CommandeDto dto) {
        if (dto == null || dto.idUtilisateur() == null) {
            throw new IllegalArgumentException("Commande sans utilisateur");
        }
        Utilisateur user = utilisateurRepository.findById(dto.idUtilisateur().id())
                .orElseThrow(() -> new ResourceAccessException("Utilisateur non trouve"));
        Commande commande = new Commande();
        commande.setDateCommande(Instant.now());
        commande.setStatut(StatutCommande.EN_ATTENTE.getLibelle());
        commande.setIdUtilisateur(user);
        Commande saved = commandeRepository.save(commande);

        Set<LigneCommande> lignes = new LinkedHashSet<>();
        if (dto.ligneCommandes() != null) {
            for (var lDto : dto.ligneCommandes()) {
                LigneCommande lc = new LigneCommande();
                lc.setIdCommande(saved);
                lc.setQte(lDto.qte());
                lc.setStatut(StatutCommande.EN_ATTENTE.getLibelle());
                Variante variante = varianteRepository.findById(lDto.idVariante().id())
                        .orElseThrow(() -> new ResourceAccessException("Variante non trouvee"));
                lc.setIdVariante(variante);
                lignes.add(ligneCommandeRepository.save(lc));
            }
        }
        saved.setLigneCommandes(lignes);
        return CommandeMapper.toDto(saved);
    }

    @Override
    public List<CommandeDto> getAllCommandes() {
        return commandeRepository.findAll().stream()
                .map(CommandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeDto getCommandeById(int id) {
        return commandeRepository.findById(id)
                .map(CommandeMapper::toDto)
                .orElseThrow(() -> new ResourceAccessException("Commande"+id+" not found"));
    }

    @Override
    @Transactional
    public CommandeDto updateLigneStatus(int idLigne, StatutCommande statut) {
        LigneCommande ligne = ligneCommandeRepository.findById(idLigne)
                .orElseThrow(() -> new ResourceAccessException("Ligne"+idLigne+" not found"));
        ligne.setStatut(statut.getLibelle());
        ligneCommandeRepository.save(ligne);

        Commande commande = commandeRepository.findById(ligne.getIdCommande().getId())
                .orElseThrow(() -> new ResourceAccessException("Commande"+ligne.getIdCommande().getId()+" not found"));

        boolean allDone = true;
        boolean anyPrep = false;
        for (LigneCommande lc : commande.getLigneCommandes()) {
            String st = lc.getId().equals(idLigne) ? statut.getLibelle() : lc.getStatut();
            if (!StatutCommande.TERMINE.getLibelle().equals(st)) {
                allDone = false;
            }
            if (StatutCommande.EN_PREPARATION.getLibelle().equals(st)) {
                anyPrep = true;
            }
        }
        String newStatut = commande.getStatut();
        if (allDone) {
            newStatut = StatutCommande.TERMINE.getLibelle();
        } else if (anyPrep) {
            newStatut = StatutCommande.EN_PREPARATION.getLibelle();
        } else {
            newStatut = StatutCommande.EN_ATTENTE.getLibelle();
        }
        commande.setStatut(newStatut);
        commandeRepository.save(commande);
        return CommandeMapper.toDto(commande);
    }
}
