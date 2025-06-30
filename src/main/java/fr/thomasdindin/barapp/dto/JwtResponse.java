package fr.thomasdindin.barapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class JwtResponse {
    private final String token;
}
