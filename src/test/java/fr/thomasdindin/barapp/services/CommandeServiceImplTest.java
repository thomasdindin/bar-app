import fr.thomasdindin.barapp.dto.CommandeDto;
import fr.thomasdindin.barapp.dto.LigneCommandeDto;
import fr.thomasdindin.barapp.dto.VarianteDto;
import fr.thomasdindin.barapp.entities.Commande;
import fr.thomasdindin.barapp.entities.LigneCommande;
import fr.thomasdindin.barapp.entities.Utilisateur;
import fr.thomasdindin.barapp.entities.Variante;
import fr.thomasdindin.barapp.enums.StatutCommande;
import fr.thomasdindin.barapp.repositories.CommandeRepository;
import fr.thomasdindin.barapp.repositories.LigneCommandeRepository;
import fr.thomasdindin.barapp.repositories.UtilisateurRepository;
import fr.thomasdindin.barapp.repositories.VarianteRepository;
import fr.thomasdindin.barapp.services.CommandeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandeServiceImplTest {

    @Mock
    private CommandeRepository commandeRepository;
    @Mock
    private LigneCommandeRepository ligneCommandeRepository;
    @Mock
    private VarianteRepository varianteRepository;
    @Mock
    private UtilisateurRepository utilisateurRepository;
    @InjectMocks
    private CommandeServiceImpl service;

    private Utilisateur user;
    private Variante variante;

    @BeforeEach
    void setup() {
        user = new Utilisateur();
        user.setId(1);
        variante = new Variante();
        variante.setId(2);
    }

    @Test
    void createCommande_buildsEntities() {
        LigneCommandeDto lcDto = new LigneCommandeDto(null, 1, null, new VarianteDto(2, "M", null, 0));
        CommandeDto dto = new CommandeDto(null, null, null, 1, Collections.singleton(lcDto));

        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(user));
        when(commandeRepository.save(any(Commande.class))).thenAnswer(inv -> {Commande c = inv.getArgument(0); c.setId(5); return c;});
        when(varianteRepository.findById(2)).thenReturn(Optional.of(variante));
        when(ligneCommandeRepository.save(any(LigneCommande.class))).thenAnswer(inv -> inv.getArgument(0));

        CommandeDto res = service.createCommande(dto);

        assertThat(res.id()).isEqualTo(5);
        verify(ligneCommandeRepository).save(any(LigneCommande.class));
    }

    @Test
    void updateLigneStatus_updatesCommandeStatut() {
        Commande commande = new Commande();
        commande.setId(10);
        commande.setStatut(StatutCommande.EN_ATTENTE.getLibelle());
        LigneCommande ligne = new LigneCommande();
        ligne.setId(3);
        ligne.setIdCommande(commande);
        ligne.setStatut(StatutCommande.EN_ATTENTE.getLibelle());
        commande.setLigneCommandes(new LinkedHashSet<>(Collections.singleton(ligne)));

        when(ligneCommandeRepository.findById(3)).thenReturn(Optional.of(ligne));
        when(commandeRepository.findById(10)).thenReturn(Optional.of(commande));
        when(ligneCommandeRepository.save(any(LigneCommande.class))).thenReturn(ligne);
        when(commandeRepository.save(any(Commande.class))).thenReturn(commande);

        CommandeDto dto = service.updateLigneStatus(3, StatutCommande.EN_PREPARATION);

        assertThat(commande.getStatut()).isEqualTo(StatutCommande.EN_PREPARATION.getLibelle());
        assertThat(dto.statut()).isEqualTo(StatutCommande.EN_PREPARATION.getLibelle());
    }

    @Test
    void getCommandeById_notFound() {
        when(commandeRepository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getCommandeById(1))
                .isInstanceOf(ResourceAccessException.class);
    }
}
