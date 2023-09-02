package com.cronos.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.api.dto.Tarefa.CriarTarefaDTO;
import com.cronos.api.dto.Tarefa.EditarTareafDTO;
import com.cronos.api.entity.Tarefa;
import com.cronos.api.service.TarefasService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    @Autowired
    private TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Cria uma tarefa")
    public ResponseEntity<Object> criarTarefa(@RequestBody CriarTarefaDTO criarTarefaDTO) {
        var newTarefa = tarefasService.criarTarefa(criarTarefaDTO);

        if (newTarefa == null) {
            return ResponseEntity.status(400).body("Erro ao criar tarefa");
        }

        return ResponseEntity.status(200).body("Sucesso ao criar tarefa");
    }

    @PutMapping(value = "/{idTarefa}")
    @Operation(summary = "Atualiza uma tarefa de acordo com o ID passado")
    public ResponseEntity<Tarefa> editarTarefa(@PathVariable("idTarefa") UUID idTarefa,
            @RequestBody EditarTareafDTO inputTarefa) {
        var tarefa = tarefasService.editarTarefa(idTarefa, inputTarefa);

        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping(value = "/delete/{idTarefa}")
    @Operation(summary = "Apaga uma tarefa de acordo com o ID passado")
    public ResponseEntity<Object> removerTarefa(@PathVariable("idTarefa") UUID idTarefa) {
        tarefasService.deletarTarefa(idTarefa);

        return ResponseEntity.ok().build();
    }
}
