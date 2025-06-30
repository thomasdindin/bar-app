package fr.thomasdindin.barapp.security;

import fr.thomasdindin.barapp.services.UtilisateurServiceImpl;
import fr.thomasdindin.barapp.utils.JwtUtil;
import fr.thomasdindin.barapp.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 1) Bean pour encoder les mots de passe (BCrypt)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 2) On expose un AuthenticationManager en utilisant AuthenticationManagerBuilder
     *    plutôt que de créer manuellement un DaoAuthenticationProvider.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       UtilisateurServiceImpl userDetailsService,
                                                       PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }

    /**
     * 3) Chaîne de filtres : on définit nos règles d’accès, CSRF, session, etc.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtUtil jwtUtil,
                                                   UtilisateurServiceImpl utilisateurServiceImpl) throws Exception {
        http
                // désactivation CSRF (adapter si vous avez des formulaires)
                .csrf(AbstractHttpConfigurer::disable)

                // on ne crée pas de session (stateless, utile pour JWT)
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // règles d’accès
                .authorizeHttpRequests(auth -> auth
                        // inscription sans auth
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        // tout le reste nécessite authentification
                        .anyRequest().authenticated())

                // on désactive formLogin et HTTP Basic par défaut
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil, utilisateurServiceImpl),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
