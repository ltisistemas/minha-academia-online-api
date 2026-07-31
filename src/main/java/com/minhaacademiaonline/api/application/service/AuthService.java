package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.adapters.in.web.dto.AuthRequestDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.AuthSignInResponseDto;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.UsuarioNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthSignInResponseDto signIn(AuthRequestDto login) {
        var usuario = usuarioService.findByEmail(login.email());
        if (usuario == null) {
            throw new UsuarioNotFoundException("User / Password is invalid");
        }

        if (!passwordEncoder.matches(login.password(), usuario.getPassword())) {
            throw new UsuarioNotFoundException("User / Password is invalid");
        }

        String token = tokenService.generateToken(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getNome()
        );

        return new AuthSignInResponseDto(usuario.getId(),usuario.getNome(), usuario.getEmail(), token);
    }
}
