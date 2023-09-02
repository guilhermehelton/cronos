package com.cronos.api.dto.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditarUsuarioDTO {
    private String nome;

    private String email;

    private String curso;
}
