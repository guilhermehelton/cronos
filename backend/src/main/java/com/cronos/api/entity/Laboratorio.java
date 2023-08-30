package com.cronos.api.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Laboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer numeroSala;

    @ManyToMany
    private List<Usuario> listaAlunos;

    @ManyToOne
    private Usuario coordenador;

    @OneToMany
    private List<Tarefa> tarefas;
}
