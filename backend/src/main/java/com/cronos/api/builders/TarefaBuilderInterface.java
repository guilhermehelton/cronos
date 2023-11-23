package com.cronos.api.builders;

import java.util.Date;
import java.util.UUID;

import com.cronos.api.entity.Laboratorio;

public interface TarefaBuilderInterface {
    TarefaBuilderInterface descricao(String descricao);

    TarefaBuilderInterface dataInicio(Date dataInicio);

    TarefaBuilderInterface dataFim(Date dataFim);

    TarefaBuilderInterface cargaHoraria(Integer cargaHoraria);

    TarefaBuilderInterface idDono(UUID idDono);

    TarefaBuilderInterface laboratorio(Laboratorio laboratorio);
}
