package com.cronos.api.builders;

import java.util.Date;
import java.util.Set;

import com.cronos.api.dto.EnumTipoPerfil;
import com.cronos.api.entity.Laboratorio;
import com.cronos.api.entity.Usuario;

public class UsuarioManualBuilder implements UsuarioBuilderInterface {
    private String nome;
    private String email;
    private Date dataNascimento;
    private String senha;
    private String curso;
    private String matricula;
    private EnumTipoPerfil role;
    private Integer cargaHorariaTotal;
    private Set<Laboratorio> laboratorios;

    @Override
    public UsuarioManualBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public UsuarioManualBuilder email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UsuarioManualBuilder dataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    @Override
    public UsuarioManualBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    @Override
    public UsuarioManualBuilder curso(String curso) {
        this.curso = curso;
        return this;
    }

    @Override
    public UsuarioManualBuilder matricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

    @Override
    public UsuarioManualBuilder role(EnumTipoPerfil role) {
        this.role = role;
        return this;
    }

    @Override
    public UsuarioManualBuilder cargaHorariaTotal(Integer cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
        return this;
    }

    @Override
    public UsuarioManualBuilder laboratorios(Set<Laboratorio> laboratorios) {
        this.laboratorios = laboratorios;
        return this;
    }

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setDataNascimento(dataNascimento);
        usuario.setCurso(curso);
        usuario.setMatricula(matricula);
        usuario.setSenha(senha);
        usuario.setCargaHorariaTotal(cargaHorariaTotal);
        usuario.setRole(role);
        usuario.setLaboratorios(laboratorios);

        return usuario;
    }

}
