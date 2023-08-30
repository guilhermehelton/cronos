package com.cronos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.api.dto.CriarUsuarioDTO;
import com.cronos.api.entity.Usuario;
import com.cronos.api.service.usuarios.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/aluno")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> criarAlunoController(@RequestBody CriarUsuarioDTO usuarioInput) {
        var newVigencia = usuarioService.criarAluno(usuarioInput);

        if (newVigencia.equals(null)) {
            return ResponseEntity.status(400).body("Falha ao criar aluno");
        }

        return ResponseEntity.status(200).body("Sucesso ao criar aluno");
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> listarAlunos() {
        return ResponseEntity.ok(usuarioService.listarAlunos());
    }

}
