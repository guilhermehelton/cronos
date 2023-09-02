package com.cronos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.api.dto.CriarTarefaDTO;
import com.cronos.api.service.TarefasService;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    @Autowired
    private TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<Object> criarTarefa(@RequestBody CriarTarefaDTO criarTarefaDTO) {
        var newTarefa = tarefasService.criarTarefa(criarTarefaDTO);

        if (newTarefa == null) {
            return ResponseEntity.status(400).body("Erro ao criar tarefa");
        }

        return ResponseEntity.status(200).body("Sucesso ao criar tarefa");
    }
}
