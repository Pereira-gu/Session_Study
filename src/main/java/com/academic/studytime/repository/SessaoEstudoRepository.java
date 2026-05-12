package com.academic.studytime.repository;

import com.academic.studytime.model.SessaoEstudo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoEstudoRepository extends JpaRepository<SessaoEstudo, Long> {

    @Query("SELECT COALESCE(SUM(s.tempoSegundos), 0) FROM SessaoEstudo s WHERE s.usuario.id = :usuarioId")
    Long obterTempoTotalSegundos(@Param("usuarioId") Long usuarioId);

    @Query("SELECT s.categoria, COALESCE(SUM(s.tempoSegundos), 0) FROM SessaoEstudo s WHERE s.usuario.id = :usuarioId GROUP BY s.categoria")
    List<Object[]> obterTempoPorCategoria(@Param("usuarioId") Long usuarioId);
    
    Page<SessaoEstudo> findAllByUsuarioId(Long usuarioId, Pageable pageable);
}
