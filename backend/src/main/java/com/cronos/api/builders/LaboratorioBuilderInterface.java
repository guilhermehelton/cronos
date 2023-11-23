package com.cronos.api.builders;

import java.util.Set;
import java.util.UUID;

import com.cronos.api.entity.Tarefa;
import com.cronos.api.entity.Usuario;

public interface LaboratorioBuilderInterface {
    LaboratorioBuilderInterface nome(String nome);

    LaboratorioBuilderInterface numeroSala(Integer sala);

    LaboratorioBuilderInterface idCoordenador(UUID idCoordenador);

    LaboratorioBuilderInterface equipe(Set<Usuario> equipe);

    LaboratorioBuilderInterface tarefas(Set<Tarefa> tarefas);
}