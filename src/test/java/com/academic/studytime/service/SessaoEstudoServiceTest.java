package com.academic.studytime.service;

import com.academic.studytime.dto.SessaoEstudoRequest;
import com.academic.studytime.model.SessaoEstudo;
import com.academic.studytime.repository.SessaoEstudoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoEstudoServiceTest {

    @Mock
    private SessaoEstudoRepository repository;

    @InjectMocks
    private SessaoEstudoService service;

    @Test
    void deveSalvarSessaoComSucesso() {
        // Cenário (Given)
        SessaoEstudoRequest request = new SessaoEstudoRequest();
        request.setCategoria("Estudo");
        request.setHorarioInicio(LocalDateTime.of(2026, 4, 30, 10, 0, 0));
        request.setHorarioFim(LocalDateTime.of(2026, 4, 30, 10, 30, 0));

        SessaoEstudo sessaoSalva = new SessaoEstudo();
        sessaoSalva.setId(1L);
        sessaoSalva.setCategoria("Estudo");
        sessaoSalva.setTempoSegundos(1800L); // 30 minutos em segundos

        when(repository.save(any(SessaoEstudo.class))).thenReturn(sessaoSalva);

        // Ação (When)
        SessaoEstudo resultado = service.salvarSessao(request);

        // Verificação (Then)
        assertNotNull(resultado);
        assertEquals(1800L, resultado.getTempoSegundos());
        assertEquals("Estudo", resultado.getCategoria());
    }

    @Test
    void deveLancarExcecaoQuandoTempoForNegativo() {
        // Cenário com horário final antes do inicial
        SessaoEstudoRequest request = new SessaoEstudoRequest();
        request.setCategoria("Estudo");
        request.setHorarioInicio(LocalDateTime.of(2026, 4, 30, 10, 30, 0));
        request.setHorarioFim(LocalDateTime.of(2026, 4, 30, 10, 0, 0));

        // Ação e Verificação
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> service.salvarSessao(request)
        );

        assertEquals("O tempo da sessão não pode ser negativo ou zero.", excecao.getMessage());
    }
}