package com.cronos.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @Column(length = 14, nullable = false, unique = true)
    private String matricula;

    @Column(length = 150, nullable = false)
    private String tipoPerfil;

    @Column(nullable = true)
    private Integer cargaHorariaTotal;

    @JsonBackReference
    @ManyToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    private Set<Laboratorio> laboratorios;
}
