package com.cronos.api.dto.Laboratorio;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarLaboratorioDTO {
    private String nome;

    private Integer numeroSala;

    private List<String> listaMatriculaAlunos;

    private String idCoordenador;
}
