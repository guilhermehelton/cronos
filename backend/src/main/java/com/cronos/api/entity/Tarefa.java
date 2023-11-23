package com.cronos.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.cronos.api.builders.TarefaManualBuilder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tarefa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 200, nullable = false)
    private String descricao;

    @Column
    private Date dataInicio;

    @Column
    private Date dataFim;

    @Column
    private Integer cargaHoraria;

    @Column(nullable = false)
    private UUID idDono;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "laboratorio_id", nullable = true)
    private Laboratorio laboratorio;

    public static TarefaManualBuilder builder() {
        return new TarefaManualBuilder();
    }
}
