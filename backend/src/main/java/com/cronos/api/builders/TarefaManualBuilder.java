package com.cronos.api.builders;

import java.util.Date;
import java.util.UUID;

import com.cronos.api.entity.Laboratorio;
import com.cronos.api.entity.Tarefa;

public class TarefaManualBuilder implements TarefaBuilderInterface {
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private Integer cargaHoraria;
    private UUID idDono;
    private Laboratorio laboratorio;

    @Override
    public TarefaManualBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public TarefaManualBuilder dataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    @Override
    public TarefaManualBuilder dataFim(Date dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    @Override
    public TarefaManualBuilder cargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
        return this;
    }

    @Override
    public TarefaManualBuilder idDono(UUID idDono) {
        this.idDono = idDono;
        return this;
    }

    @Override
    public TarefaManualBuilder laboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
        return this;
    }

    public Tarefa build() {
        Tarefa tarefa = new Tarefa();
        tarefa.setCargaHoraria(cargaHoraria);
        tarefa.setDataFim(dataFim);
        tarefa.setDataInicio(dataInicio);
        tarefa.setDescricao(descricao);
        tarefa.setIdDono(idDono);
        tarefa.setLaboratorio(laboratorio);

        return tarefa;
    }
}
