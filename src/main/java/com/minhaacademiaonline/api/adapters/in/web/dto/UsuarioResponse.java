package com.minhaacademiaonline.api.adapters.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        String nif,
        Integer Idade,
        String username,
        LocalDateTime deletedAt
) {
}
