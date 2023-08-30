package com.cronos.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarUsuarioDTO {
    private String nome;

    private String email;

    private Date dataNascimento;

    private String senha;

    private String curso;

    private String matricula;

    private EnumTipoPerfil tipoPerfil;
}
