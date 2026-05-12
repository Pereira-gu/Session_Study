package com.academic.studytime.service;

import com.academic.studytime.dto.SEResponse;
import com.academic.studytime.dto.SessaoEstudoMapper;
import com.academic.studytime.dto.SessaoEstudoRequest;
import com.academic.studytime.model.SessaoEstudo;
import com.academic.studytime.repository.SessaoEstudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SessaoEstudoService {

    private final SessaoEstudoRepository repository;
    private final SessaoEstudoMapper mapper;

    @CacheEvict(value = "tempoTotalCache", allEntries = true)
    public SessaoEstudo salvarSessao(SessaoEstudoRequest request) {
        SessaoEstudo sessao = mapper.toEntity(request);

        long segundosCalculados = Duration.between(
                request.getHorarioInicio(),
                request.getHorarioFim()
        ).getSeconds();

        if (segundosCalculados <= 0) {
            throw new IllegalArgumentException("O tempo da sessão não pode ser negativo ou zero.");
        }

        sessao.setTempoSegundos(segundosCalculados);
        sessao.setDataCriacao(request.getHorarioInicio().toLocalDate());
        
        // Atribuindo o status como FINALIZADA por padrão,
        // já que estamos recebendo um horário de fim na requisição
        sessao.setStatus(SessaoEstudo.StatusSessao.FINALIZADA);

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

    public Page<SEResponse> listarSessoes(Pageable pageable) {
        Page<SessaoEstudo> pagina = repository.findAll(pageable);
        return pagina.map(mapper::toResponse);
    }
}
