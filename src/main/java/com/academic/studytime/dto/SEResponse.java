package com.academic.studytime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class SEResponse {
    private Long id;
    private String categoria;
    private Long tempoSegundos;
    private LocalDate dataCriacao;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
}