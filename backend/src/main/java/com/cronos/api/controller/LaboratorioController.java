package com.cronos.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.api.dto.Laboratorio.CriarLaboratorioDTO;
import com.cronos.api.entity.Laboratorio;
import com.cronos.api.entity.Tarefa;
import com.cronos.api.entity.Usuario;
import com.cronos.api.service.LaboratorioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {
    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping()
    @Operation(summary = "Criar um laboratório")
    public ResponseEntity<Object> criarLaboratorio(@RequestBody CriarLaboratorioDTO criarLaboratorioDTO) {
        var newLaboratorio = laboratorioService.criarLaboratorio(criarLaboratorioDTO);

        if (newLaboratorio == null) {
            return ResponseEntity.status(400).body("Erro ao criar laboratório");
        }

        return ResponseEntity.status(200).body("Sucesso ao criar laboratório");
    }

    @PostMapping("/add-membro/{idLaboratorio}")
    @Operation(summary = "Adiciona um membro na equipe do laboratório")
    public ResponseEntity<Usuario> addMembro(@PathVariable("idLaboratorio") UUID idLaboratorio,
            @RequestParam(required = true, name = "matricula") String matricula) {
        var newMembro = laboratorioService.addMembroLaboratorio(matricula, idLaboratorio);

        if (newMembro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(newMembro);
    }

    @PutMapping("/remove-membro/{idLaboratorio}")
    @Operation(summary = "Remove um membro na equipe do laboratório")
    public ResponseEntity<Usuario> removeMembro(@PathVariable("idLaboratorio") UUID idLaboratorio,
            @RequestParam(required = true, name = "matricula") String matricula) {
        var membro = laboratorioService.removerMembroLaboratorio(matricula, idLaboratorio);

        if (membro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idLaboratorio}")
    @Operation(summary = "Retorna um laboratório de acordo com o ID passado")
    public ResponseEntity<Laboratorio> listarLaboratorio(@PathVariable("idLaboratorio") UUID idLaboratorio) {
        var responseBody = laboratorioService.listarLaboratorio(idLaboratorio);

        return ResponseEntity.status(200).body(responseBody);
    }

    @GetMapping("/todos")
    @Operation(summary = "Lista todos os laboratórios cadastrados")
    public ResponseEntity<List<Laboratorio>> listarTodosLaboratorios() {
        var responseBody = laboratorioService.listarTodosLaboratorios();

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/listar-por-membro/{idMembro}")
    @Operation(summary = "Lista os laboratórios que um usuário participa")
    public ResponseEntity<List<Laboratorio>> listaLaboratoriosPorMembro(@PathVariable("idMembro") UUID idMembro) {
        var laboratorios = laboratorioService.listarLaboratorioByMembro(idMembro);

        return ResponseEntity.ok(laboratorios);
    }

    @GetMapping("/listar-equipe/{idLaboratorio}")
    @Operation(summary = "Lista toda a equipe do laboratório")
    public ResponseEntity<List<Usuario>> listarEquipeLaboratorio(@PathVariable("idLaboratorio") UUID idLaboratorio) {
        var equipe = laboratorioService.listarMembrosLaboratorio(idLaboratorio);

        return ResponseEntity.ok(equipe);
    }

    @GetMapping("/listar-tarefas/{idLaboratorio}")
    @Operation(summary = "Lista todas as tarefas de um laboratório")
    public ResponseEntity<List<Tarefa>> listarTodasTarefasLaboratorio(
            @PathVariable("idLaboratorio") UUID idLaboratorio) {
        var tarefas = laboratorioService.listarTarefasLaboratorio(idLaboratorio);

        return ResponseEntity.ok(tarefas);
    }
}
