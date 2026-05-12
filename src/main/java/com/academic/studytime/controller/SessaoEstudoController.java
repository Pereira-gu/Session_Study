package com.academic.studytime.controller;

import com.academic.studytime.dto.SEResponse;
import com.academic.studytime.dto.SessaoEstudoMapper;
import com.academic.studytime.dto.SessaoEstudoRequest;
import com.academic.studytime.model.SessaoEstudo;
import com.academic.studytime.service.SessaoEstudoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/sessoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SessaoEstudoController {

    private final SessaoEstudoService service;
    private final SessaoEstudoMapper mapper;

    @PostMapping
    public ResponseEntity<SEResponse> criarSessao(@Valid @RequestBody SessaoEstudoRequest request) {
        SessaoEstudo novaSessao = service.salvarSessao(request);
        
        SEResponse response = mapper.toResponse(novaSessao);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<SEResponse>> listarSessoes(
            @PageableDefault(page = 0, size = 10, sort = "horarioInicio", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SEResponse> sessoes = service.listarSessoes(pageable);
        return ResponseEntity.ok(sessoes);
    }

    @GetMapping("/tempo-total")
    public ResponseEntity<Long> obterTempoTotal() {
        Long totalSegundos = service.obterTempoTotalSegundos();
        return ResponseEntity.ok(totalSegundos);
    }

    @GetMapping("/tempo-por-categoria")
    public ResponseEntity<Map<String, Long>> obterTempoPorCategoria() {
        Map<String, Long> tempoPorCategoria = service.obterTempoPorCategoria();
        return ResponseEntity.ok(tempoPorCategoria);
    }
}
