import fr.thomasdindin.barapp.dto.UserRegistrationDto;
import fr.thomasdindin.barapp.entities.Utilisateur;
import fr.thomasdindin.barapp.enums.Role;
import fr.thomasdindin.barapp.repositories.UtilisateurRepository;
import fr.thomasdindin.barapp.services.UtilisateurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceImplTest {

    @Mock
    private UtilisateurRepository repository;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UtilisateurServiceImpl service;

    private UserRegistrationDto dto;
    private Utilisateur user;

    @BeforeEach
    void setup() {
        dto = new UserRegistrationDto();
        dto.setEmail("test@ex.com");
        dto.setNom("n");
        dto.setPrenom("p");
        dto.setPassword("pw");
        dto.setRole(Role.CLIENT);

        user = new Utilisateur();
        user.setId(1);
        user.setEmail(dto.getEmail());
        user.setMdp("enc");
        user.setRole(Role.CLIENT);
    }

    @Test
    void register_createsUser() {
        when(repository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(repository.save(any(Utilisateur.class))).thenReturn(user);
        when(encoder.encode("pw")).thenReturn("enc");

        Utilisateur created = service.register(dto);

        assertThat(created.getEmail()).isEqualTo("test@ex.com");
        verify(repository).save(any(Utilisateur.class));
    }

    @Test
    void register_existingEmail_throws() {
        when(repository.existsByEmail(dto.getEmail())).thenReturn(true);
        assertThatThrownBy(() -> service.register(dto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getByEmail_whenFound() {
        when(repository.findByEmail("a@b.c")).thenReturn(Optional.of(user));
        Utilisateur res = service.getByEmail("a@b.c");
        assertThat(res).isSameAs(user);
    }

    @Test
    void getByEmail_whenMissing_throws() {
        when(repository.findByEmail("a@b.c")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getByEmail("a@b.c"))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    void loadUserByUsername_returnsUserDetails() {
        when(repository.findByEmail("x@y.z")).thenReturn(Optional.of(user));
        UserDetails details = service.loadUserByUsername("x@y.z");
        assertThat(details.getUsername()).isEqualTo("test@ex.com");
    }

    @Test
    void loadUserByUsername_notFound() {
        when(repository.findByEmail("x@y.z")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.loadUserByUsername("x@y.z"))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
