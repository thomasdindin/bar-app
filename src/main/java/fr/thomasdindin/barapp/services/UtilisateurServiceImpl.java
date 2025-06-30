package fr.thomasdindin.barapp.services;

import fr.thomasdindin.barapp.dto.UserRegistrationDto;
import fr.thomasdindin.barapp.entities.Utilisateur;
import fr.thomasdindin.barapp.enums.Role;
import fr.thomasdindin.barapp.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {
    private final UtilisateurRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Utilisateur register(UserRegistrationDto dto) {
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        Utilisateur u = new Utilisateur();
        u.setNom(dto.getNom());
        u.setPrenom(dto.getPrenom());
        u.setEmail(dto.getEmail());
        u.setMdp(passwordEncoder.encode(dto.getPassword()));
        u.setRole(dto.getRole());
        return userRepo.save(u);
    }

    @Override
    public Utilisateur getByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    }

    /**
     * Méthode appelée par Spring Security pour l’authentification.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur u = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        return new User(
                u.getEmail(),
                u.getMdp(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + u.getRole()))
        );
    }
}
