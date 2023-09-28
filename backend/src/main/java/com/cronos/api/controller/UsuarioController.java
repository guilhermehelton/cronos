package com.cronos.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.api.dto.Usuario.CriarUsuarioDTO;
import com.cronos.api.dto.Usuario.EditarUsuarioDTO;
import com.cronos.api.entity.Usuario;
import com.cronos.api.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/alunos")
    @Operation(summary = "Lista todos os usuários com perfil de ALUNO")
    public ResponseEntity<List<Usuario>> listarUsuariosAlunos() {
        return ResponseEntity.ok(usuarioService.listarUsuariosAlunos());
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários do sistema")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/por-matricula/{matricula}")
    @Operation(summary = "Busca usuário por matrícula")
    public ResponseEntity<Usuario> buscarUsuarioPorMatricula(@PathVariable("matricula") String matricula) {
        Usuario usuario = usuarioService.buscarPorMatricula(matricula);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{idUsuario}")
    @Operation(summary = "Edita um usuário de acordo com o ID passado")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable("idUsuario") UUID idUsuario,
            @RequestBody EditarUsuarioDTO usuarioInput) {
        var newUsuario = usuarioService.editarUsuario(idUsuario, usuarioInput);

        if (newUsuario == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(newUsuario);
    }
}
