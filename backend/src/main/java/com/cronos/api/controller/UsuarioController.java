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
import com.cronos.api.service.UsuarioService;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> criarUsuarioController(@RequestBody CriarUsuarioDTO usuarioInput) {
        var newUsuario = usuarioService.criarUsuario(usuarioInput);

        if (newUsuario == null) {
            return ResponseEntity.status(400).body("Matrícula já cadastrada");
        }

        return ResponseEntity.status(200).body("Sucesso ao criar usuario");
    }

    @GetMapping("/alunos")
    public ResponseEntity<List<Usuario>> listarUsuariosAlunos() {
        return ResponseEntity.ok(usuarioService.listarUsuariosAlunos());
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }
}
