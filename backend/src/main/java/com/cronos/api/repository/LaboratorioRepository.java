package com.cronos.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cronos.api.entity.Laboratorio;
import com.cronos.api.entity.Tarefa;
import com.cronos.api.entity.Usuario;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, UUID> {
    @Query(value = "SELECT lab FROM Laboratorio lab JOIN lab.equipe equi WHERE equi.id = :userId")
    List<Laboratorio> findLaboratorioByUsuarioId(@Param("userId") UUID userId);

    @Query(value = "SELECT lab.equipe FROM Laboratorio lab WHERE lab.id = :labId")
    List<Usuario> findAllMembrosLaboratorio(@Param("labId") UUID labId);

    @Query(value = "SELECT lab.tarefas FROM Laboratorio lab WHERE lab.id = :labId")
    List<Tarefa> findAllTarefasLaboratorio(@Param("labId") UUID labId);
}
