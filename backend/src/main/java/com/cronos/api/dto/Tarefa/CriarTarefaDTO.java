package com.cronos.api.dto.Tarefa;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarTarefaDTO {
    private String descricao;

    private Date dataInicio;

    private Date dataFim;

    private Integer cargaHoraria;

    private String idDono;

    private String idLaboratorio;
}
