package com.minhaacademiaonline.api.adapters.in.web.dto;

import java.util.UUID;

public record UsuarioCreateRequestDto(
        String name,
        String email,
        String password) {
}
