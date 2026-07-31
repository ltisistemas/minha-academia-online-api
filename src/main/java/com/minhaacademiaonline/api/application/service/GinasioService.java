package com.minhaacademiaonline.api.application.service;

import com.minhaacademiaonline.api.adapters.in.web.dto.GinasioCreateRequest;
import com.minhaacademiaonline.api.adapters.in.web.dto.GinasioUsuarioResponse;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.SubscriptionPlanNotFoundException;
import com.minhaacademiaonline.api.application.mappers.GinasioUsuarioMapper;
import com.minhaacademiaonline.api.domain.entites.*;
import com.minhaacademiaonline.api.infra.repositories.GinasioRepository;
import com.minhaacademiaonline.api.infra.repositories.GinasioUsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GinasioService {
    private final GinasioRepository ginasioRepository;
    private final GinasioUsuarioRepository ginasioUsuarioRepository;
    private final GinasioUsuarioMapper ginasioUsuarioMapper;

    public List<GinasioUsuarioResponse> findAll(Usuario usuario) {
        return ginasioUsuarioMapper.toGinasioUsuarioResponse(
                ginasioUsuarioRepository.findGinasioUsuariosByUsuario(usuario)
        );
    }

    @Transactional
    public GinasioUsuarioResponse create(GinasioCreateRequest dto, Usuario usuario) {
        var plan = findPlanById(dto.planId());
        if (plan == null) {
            throw new SubscriptionPlanNotFoundException("Subscription Plan not found");
        }

        var gym =  Ginasio
                .builder()
                .plan(plan)
                .nome(dto.nome())
                .amount(plan.getPrice())
                .build();

        if (dto.address().isPresent()) gym.setEndereco(dto.address().get());
        if (dto.lat().isPresent()) gym.setLat(dto.lat().get());
        if (dto.lng().isPresent()) gym.setLng(dto.lng().get());

        var ginasio = ginasioRepository.save(gym);

        var ginasioUsuarioRequest = GinasioUsuario
                .builder()
                .ginasio(ginasio)
                .usuario(usuario)
                .role(Role.OWNER)
                .build();

        if (dto.graduacao().isPresent()) ginasioUsuarioRequest.setGraduacao(dto.graduacao().get());
        if (dto.graus().isPresent()) ginasioUsuarioRequest.setGraus(dto.graus().get());

        var ginasioUsuario = ginasioUsuarioRepository.save(ginasioUsuarioRequest);

        return  ginasioUsuarioMapper.toGinasioUsuarioResponse(ginasioUsuario);
    }

    private SubscriptionPlan findPlanById(UUID id) {
        return Arrays
                .stream(SubscriptionPlan.values())
                .filter(plan -> plan.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
