package com.minhaacademiaonline.api.domain.entites;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ginasios")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@EqualsAndHashCode(of = "id")
public class Ginasio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = true)
    private String endereco;
    @Column(nullable = true)
    private String lat;
    @Column(nullable = true)
    private String lng;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SubscriptionPlan plan = SubscriptionPlan.FREE;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;
}
