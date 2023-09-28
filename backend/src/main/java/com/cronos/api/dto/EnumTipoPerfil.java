package com.cronos.api.dto;

public enum EnumTipoPerfil {
    ALUNO("ALUNO"),
    COORDENADOR("COORDENADOR");

    private String role;

    EnumTipoPerfil(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
