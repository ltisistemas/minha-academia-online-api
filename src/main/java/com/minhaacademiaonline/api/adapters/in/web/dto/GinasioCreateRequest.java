package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entites.Graduacao;
import com.minhaacademiaonline.api.domain.entites.Role;

import java.util.Optional;
import java.util.UUID;

public record GinasioCreateRequest(
        UUID planId,
        String nome,
        Role role,
        Optional<Integer> graus,
        Optional<Graduacao> graduacao,
        Optional<String> address,
        Optional<String> lat,
        Optional<String> lng) {
}
