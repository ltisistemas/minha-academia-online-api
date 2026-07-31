package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entites.Graduacao;
import com.minhaacademiaonline.api.domain.entites.Role;

import java.util.UUID;

public record GinasioUsuarioResponse(
        UUID id,
        GinasioResponse ginasio,
        UsuarioResponse usuario,
        Role role,
        Graduacao graduacao,
        Integer graus
) {
}
