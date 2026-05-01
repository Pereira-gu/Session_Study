package com.academic.studytime.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
public class SessaoEstudoRequest {
    private String categoria;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
}