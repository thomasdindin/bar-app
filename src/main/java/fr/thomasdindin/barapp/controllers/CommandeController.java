package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.dto.CommandeDto;
import fr.thomasdindin.barapp.enums.StatutCommande;
import fr.thomasdindin.barapp.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeService commandeService;

    @PostMapping
    public ResponseEntity<CommandeDto> createCommande(@RequestBody CommandeDto commande) {
        return ResponseEntity.ok(commandeService.createCommande(commande));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<CommandeDto>> getCommandesByClientId(@PathVariable int id) {
        return ResponseEntity.ok(commandeService.getCommandesByClientId(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<List<CommandeDto>> getAll() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> getById(@PathVariable int id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @PutMapping("/lignes/{id}")
    @PreAuthorize("hasRole('BARMAKER')")
    public ResponseEntity<CommandeDto> updateLigneStatus(@PathVariable int id,
                                                         @RequestParam StatutCommande statut) {
        return ResponseEntity.ok(commandeService.updateLigneStatus(id, statut));
    }
}
