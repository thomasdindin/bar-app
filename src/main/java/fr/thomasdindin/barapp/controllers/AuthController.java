package fr.thomasdindin.barapp.controllers;

import fr.thomasdindin.barapp.dto.JwtResponse;
import fr.thomasdindin.barapp.dto.LoginRequest;
import fr.thomasdindin.barapp.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService    userDetailsService;
    private final JwtUtil               jwtUtil;

    @PostMapping(path = "/login")
    public ResponseEntity<JwtResponse> login(
            @Validated @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(request.getEmail());

            String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity
                    .ok()
                    .body(new JwtResponse(jwt, userDetails.getAuthorities().stream()
                            .findFirst()
                            .map(Object::toString)
                            .orElse("CLIENT")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new JwtResponse("Authentication failed", ""));
        }

    }
}

