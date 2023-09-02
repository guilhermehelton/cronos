package com.cronos.api.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Laboratorio implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(length = 20, nullable = false)
        private String nome;

        @Column(nullable = false)
        private Integer numeroSala;

        @Column(nullable = false)
        private UUID idCoordenador;

        @JsonManagedReference
        @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinTable(name = "laboratorio_equipe", joinColumns = {
                        @JoinColumn(name = "laboratorio_id", referencedColumnName = "id")
        }, inverseJoinColumns = {
                        @JoinColumn(name = "usuario_id", referencedColumnName = "id")
        })
        private Set<Usuario> equipe;

        @JsonManagedReference
        @OneToMany(mappedBy = "laboratorio")
        private Set<Tarefa> tarefas;
}
