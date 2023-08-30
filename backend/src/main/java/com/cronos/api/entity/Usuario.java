package com.cronos.api.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity()
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String email;

    @Column
    private Date dataNascimento;

    @Column(length = 11, nullable = false)
    private String senha;

    @Column(length = 64, nullable = true)
    private String curso;

    @Column(length = 14, nullable = false)
    private String matricula;

    @Column(length = 150, nullable = false)
    private String tipoPerfil;

    @Column(nullable = true)
    private Integer cargaHorariaTotal;

    @ManyToMany
    private List<Laboratorio> laboratorios;
}
