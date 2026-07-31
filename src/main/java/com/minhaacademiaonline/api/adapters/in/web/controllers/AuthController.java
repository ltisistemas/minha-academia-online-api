package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.minhaacademiaonline.api.adapters.in.web.dto.*;
import com.minhaacademiaonline.api.application.service.AuthService;
import com.minhaacademiaonline.api.application.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;
    private final AuthService authService;

    @PostMapping("sign-up")
    public ResponseEntity<AuthSignInResponseDto> signUp(@RequestBody UsuarioCreateRequestDto usuario) {
        return ResponseEntity.ok(usuarioService.create(usuario));
    }

    @PostMapping("sign-in")
    public ResponseEntity<AuthSignInResponseDto> signIn(@RequestBody AuthRequestDto login) {
        return ResponseEntity.ok(authService.signIn(login));
    }
}
