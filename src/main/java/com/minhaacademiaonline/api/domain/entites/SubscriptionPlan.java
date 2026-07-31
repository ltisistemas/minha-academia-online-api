package com.minhaacademiaonline.api.domain.entites;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
//import java.util.UUID;

@AllArgsConstructor
@Getter
public enum SubscriptionPlan {
    FREE(UUID.fromString("3d61d73f-aeab-497c-89b8-b575b904b1fb"),"FREE", "", BigDecimal.ZERO, 20),
    ATHLETIC(UUID.fromString("a4c5c9c8-1690-4190-83ec-b7bb4f1ea30c"),"ATHLETIC", "", new BigDecimal("150.00"), 20),
    COMPETITION(UUID.fromString("ca4a73b8-6f8c-4a01-b279-026366162e2a"),"COMPETITION", "", new BigDecimal("350.00"), null),

    // Plano exclusivo a ser criado para instituições de caridades, como igrejas e escolas em comunidades
    BENEFICENT(UUID.fromString("df904685-5899-43dc-9340-4db8eef9e326"), "BENEFICIT", "", BigDecimal.ZERO, null);

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Integer quantity;
}
