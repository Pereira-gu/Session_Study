package com.academic.studytime.service;

import com.academic.studytime.dto.SessaoEstudoRequest;
import com.academic.studytime.model.SessaoEstudo;
import com.academic.studytime.repository.SessaoEstudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SessaoEstudoService {

    private final SessaoEstudoRepository repository;

    @CacheEvict(value = "tempoTotalCache", allEntries = true)
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

    @Cacheable(value = "tempoTotalCache")
    public Long obterTempoTotalSegundos() {
        return repository.obterTempoTotalSegundos();
    }

    public Map<String, Long> obterTempoPorCategoria() {
        List<Object[]> resultados = repository.obterTempoPorCategoria();
        Map<String, Long> tempoPorCategoria = new HashMap<>();

        for (Object[] resultado : resultados) {
            String categoria = (String) resultado[0];
            Long tempoSegundos = (Long) resultado[1];
            tempoPorCategoria.put(categoria, tempoSegundos);
        }

        return tempoPorCategoria;
    }
}