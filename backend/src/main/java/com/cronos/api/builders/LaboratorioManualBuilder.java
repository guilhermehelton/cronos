package com.cronos.api.builders;

import java.util.Set;
import java.util.UUID;

import com.cronos.api.entity.Laboratorio;
import com.cronos.api.entity.Tarefa;
import com.cronos.api.entity.Usuario;

public class LaboratorioManualBuilder implements LaboratorioBuilderInterface {
    private String nome;
    private Integer numeroSala;
    private UUID idCoordenador;
    private Set<Usuario> equipe;
    private Set<Tarefa> tarefas;

    @Override
    public LaboratorioManualBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public LaboratorioManualBuilder numeroSala(Integer sala) {
        this.numeroSala = sala;
        return this;
    }

    @Override
    public LaboratorioManualBuilder idCoordenador(UUID idCoordenador) {
        this.idCoordenador = idCoordenador;
        return this;
    }

    @Override
    public LaboratorioManualBuilder equipe(Set<Usuario> equipe) {
        this.equipe = equipe;
        return this;
    }

    @Override
    public LaboratorioManualBuilder tarefas(Set<Tarefa> tarefas) {
        this.tarefas = tarefas;
        return this;
    }

    public Laboratorio build() {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setEquipe(equipe);
        laboratorio.setIdCoordenador(idCoordenador);
        laboratorio.setNome(nome);
        laboratorio.setNumeroSala(numeroSala);
        laboratorio.setTarefas(tarefas);

        return laboratorio;
    }
}
