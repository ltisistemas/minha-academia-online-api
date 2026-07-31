package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entites.SubscriptionPlan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record GinasioResponse(
        UUID id,
        String nome,
        String endereco,
        String lat,
        String lng,
        SubscriptionPlan plan,
        BigDecimal amount,
        LocalDateTime deletedAt
) {
}
