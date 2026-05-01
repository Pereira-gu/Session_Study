package com.academic.studytime.service;

import com.academic.studytime.dto.SessaoEstudoRequest;
import com.academic.studytime.model.SessaoEstudo;
import com.academic.studytime.repository.SessaoEstudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class SessaoEstudoService {

    private final SessaoEstudoRepository repository;

    public SessaoEstudo salvarSessao(SessaoEstudoRequest request) {
        SessaoEstudo sessao = new SessaoEstudo();
        sessao.setCategoria(request.getCategoria());

        sessao.setHorarioInicio(request.getHorarioInicio());
        sessao.setHorarioFim(request.getHorarioFim());

        long segundosCalculados = Duration.between(
                request.getHorarioInicio(),
                request.getHorarioFim()
        ).getSeconds();

        if (segundosCalculados <= 0) {
            throw new IllegalArgumentException("O tempo da sessão não pode ser negativo ou zero.");
        }

        sessao.setTempoSegundos(segundosCalculados);
        sessao.setDataCriacao(request.getHorarioInicio().toLocalDate());

        return repository.save(sessao);
    }

    public Long obterTempoTotalSegundos() {
        return repository.obterTempoTotalSegundos();
    }
}