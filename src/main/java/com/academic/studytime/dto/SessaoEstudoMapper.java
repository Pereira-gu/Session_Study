package com.academic.studytime.dto;

import com.academic.studytime.model.SessaoEstudo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SessaoEstudoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tempoSegundos", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "status", ignore = true)
    SessaoEstudo toEntity(SessaoEstudoRequest request);

    SEResponse toResponse(SessaoEstudo sessaoEstudo);
}
