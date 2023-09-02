package com.cronos.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.api.dto.CriarLaboratorioDTO;
import com.cronos.api.entity.Laboratorio;
import com.cronos.api.service.LaboratorioService;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {
    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping()
    public ResponseEntity<Object> criarLaboratorio(@RequestBody CriarLaboratorioDTO criarLaboratorioDTO) {
        var newLaboratorio = laboratorioService.criarLaboratorio(criarLaboratorioDTO);

        if (newLaboratorio == null) {
            return ResponseEntity.status(400).body("Erro ao criar laboratório");
        }

        return ResponseEntity.status(200).body("Sucesso ao criar laboratório");
    }

    @GetMapping("/{idLaboratorio}")
    public ResponseEntity<Laboratorio> listarLaboratorio(@PathVariable("idLaboratorio") UUID idLaboratorio) {
        var responseBody = laboratorioService.listarLaboratorio(idLaboratorio);

        return ResponseEntity.status(200).body(responseBody);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Laboratorio>> listarTodosLaboratorios() {
        var responseBody = laboratorioService.listarTodosLaboratorios();

        return ResponseEntity.ok(responseBody);
    }
}
