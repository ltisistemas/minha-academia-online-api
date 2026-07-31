package com.minhaacademiaonline.api.domain.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "ginasio_usuarios")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@EqualsAndHashCode(of = "id")
public class GinasioUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ginasio_id")
    private Ginasio ginasio;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Graduacao graduacao;

    @Column(nullable = true)
    @Min(0)
    @Max(15)
    private Integer graus = 0;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
