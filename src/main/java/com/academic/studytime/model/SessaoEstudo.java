package com.academic.studytime.model; // Ajuste para minúsculo para seguir a convenção Java

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_sessao_estudo")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SessaoEstudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoria;

    private Long tempoSegundos;

    private LocalDate dataCriacao;

    @Column(nullable = false)
    private LocalDateTime horarioInicio;

    private LocalDateTime horarioFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSessao status;

    public enum StatusSessao{ INICIADA, FINALIZADA}
}