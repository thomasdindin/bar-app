package fr.thomasdindin.barapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class JwtResponse {
    private final String token;
    private final String userRole;
    private final String userId;
}
