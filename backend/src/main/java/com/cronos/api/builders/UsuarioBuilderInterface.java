package com.cronos.api.builders;

import java.util.Date;
import java.util.Set;

import com.cronos.api.dto.EnumTipoPerfil;
import com.cronos.api.entity.Laboratorio;

public interface UsuarioBuilderInterface {
    UsuarioBuilderInterface nome(String nome);

    UsuarioBuilderInterface email(String email);

    UsuarioBuilderInterface dataNascimento(Date dataNascimento);

    UsuarioBuilderInterface senha(String senha);

    UsuarioBuilderInterface curso(String curso);

    UsuarioBuilderInterface matricula(String matricula);

    UsuarioBuilderInterface role(EnumTipoPerfil role);

    UsuarioBuilderInterface cargaHorariaTotal(Integer cargaHorariaTotal);

    UsuarioBuilderInterface laboratorios(Set<Laboratorio> laboratorios);
}
