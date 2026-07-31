package com.minhaacademiaonline.api.application.mappers;

import com.minhaacademiaonline.api.adapters.in.web.dto.GinasioUsuarioResponse;
import com.minhaacademiaonline.api.domain.entites.GinasioUsuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GinasioUsuarioMapper {
    @Mapping(target = "id", source = "result.id")
    @Mapping(target = "ginasio", source = "result.ginasio")
    @Mapping(target = "usuario", source = "result.usuario")
    @Mapping(target = "role", source = "result.role")
    @Mapping(target = "graduacao", source = "result.graduacao")
    @Mapping(target = "graus", source = "result.graus")
    GinasioUsuarioResponse toGinasioUsuarioResponse(GinasioUsuario result);

    List<GinasioUsuarioResponse> toGinasioUsuarioResponse(List<GinasioUsuario> result);
}
