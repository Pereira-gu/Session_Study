package com.academic.studytime.repository;

import com.academic.studytime.model.SessaoEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoEstudoRepository extends JpaRepository<SessaoEstudo, Long> {
    @Query("SELECT COALESCE(SUM(s.tempoSegundos), 0) FROM SessaoEstudo s")
    Long obterTempoTotalSegundos();
}
