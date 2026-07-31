package com.minhaacademiaonline.api.infra.repositories;

import com.minhaacademiaonline.api.domain.entites.Ginasio;
import com.minhaacademiaonline.api.domain.entites.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GinasioRepository  extends JpaRepository<Ginasio, UUID> {

}
