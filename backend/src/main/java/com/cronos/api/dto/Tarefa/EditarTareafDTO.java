package com.cronos.api.dto.Tarefa;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditarTareafDTO {
    private String descricao;

    private Date dataInicio;

    private Date dataFim;

    private Integer cargaHoraria;
}
