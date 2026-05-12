package com.academic.studytime.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
public class SessaoEstudoRequest {
    private Long id;
    
    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;
    
    @NotNull(message = "O horário de início é obrigatório")
    private LocalDateTime horarioInicio;
    
    @NotNull(message = "O horário de fim é obrigatório")
    private LocalDateTime horarioFim;
}
