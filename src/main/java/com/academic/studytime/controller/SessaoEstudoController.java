package com.academic.studytime.controller;

import com.academic.studytime.dto.SEResponse;
import com.academic.studytime.dto.SessaoEstudoRequest;
import com.academic.studytime.model.SessaoEstudo;
import com.academic.studytime.service.SessaoEstudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SessaoEstudoController {

    private final SessaoEstudoService service;

    @PostMapping
    public ResponseEntity<SessaoEstudo> criarSessao(@RequestBody SessaoEstudoRequest request) {
        SessaoEstudo novaSessao = service.salvarSessao(request);

        SEResponse response = new SEResponse(
                novaSessao.getId(),
                novaSessao.getCategoria(),
                novaSessao.getTempoSegundos(),
                novaSessao.getDataCriacao(),
                novaSessao.getHorarioInicio(),
                novaSessao.getHorarioFim()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSessao);
    }

    @GetMapping("/tempo-total")
    public ResponseEntity<Long> obterTempoTotal() {
        Long totalSegundos = service.obterTempoTotalSegundos();
        return ResponseEntity.ok(totalSegundos);
    }
}