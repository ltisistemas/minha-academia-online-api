package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.minhaacademiaonline.api.adapters.in.web.dto.GinasioCreateRequest;
import com.minhaacademiaonline.api.adapters.in.web.dto.GinasioUsuarioResponse;
import com.minhaacademiaonline.api.application.service.GinasioService;
import com.minhaacademiaonline.api.domain.entites.GinasioUsuario;
import com.minhaacademiaonline.api.domain.entites.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class GinasioController {
    private final GinasioService ginasioService;

    @GetMapping("ginasio")
    public ResponseEntity<List<GinasioUsuarioResponse>>  getAll() {
        UserDetails user = (UserDetails) Objects
                .requireNonNull(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                ).getPrincipal();

        assert user != null;
        Usuario usuario = ((Usuario) user);

        return ResponseEntity.ok(ginasioService.findAll(usuario));
    }

    @PostMapping("ginasio")
    public ResponseEntity<GinasioUsuarioResponse> create(@RequestBody GinasioCreateRequest dto) {
        UserDetails user = (UserDetails) Objects
                .requireNonNull(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                ).getPrincipal();
        assert user != null;
        var usuario = ((Usuario) user);

        return ResponseEntity.ok(ginasioService.create(dto, usuario));
    }
}
