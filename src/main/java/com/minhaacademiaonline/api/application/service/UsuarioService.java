package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.adapters.in.web.dto.*;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.*;
import com.minhaacademiaonline.api.domain.entites.*;
import com.minhaacademiaonline.api.infra.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService  tokenService;

    @Override
    public @Nullable UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username).orElse(null);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public AuthSignInResponseDto create(UsuarioCreateRequestDto dto) throws UsuarioException {
        var findUser = usuarioRepository.findByEmail(dto.email());
        if (findUser.isPresent()) {
            throw new UsuarioEmailExists("User with email " + dto.email() + " already exists");
        }

        Usuario usuario = Usuario
                .builder()
                .nome(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        var user = usuarioRepository.save(usuario);

        String token = tokenService.generateToken(
                user.getId(),
                user.getUsername(),
                user.getNome()
        );

        return new AuthSignInResponseDto(user.getId(),user.getNome(), user.getEmail(), token);
    }
}
