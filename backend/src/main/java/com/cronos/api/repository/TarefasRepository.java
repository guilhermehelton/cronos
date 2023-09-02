package com.cronos.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cronos.api.entity.Tarefa;

public interface TarefasRepository extends JpaRepository<Tarefa, UUID> {

}
