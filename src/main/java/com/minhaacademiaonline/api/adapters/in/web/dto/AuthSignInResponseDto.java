package com.minhaacademiaonline.api.adapters.in.web.dto;

import com.minhaacademiaonline.api.domain.entites.SubscriptionPlan;

import java.util.UUID;

public record AuthSignInResponseDto(
        UUID sub,
        String userName,
        String userEmail,
        String access_token
) {
}
