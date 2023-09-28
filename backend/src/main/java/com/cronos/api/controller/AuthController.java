package com.cronos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.api.config.TokenService;
import com.cronos.api.dto.Usuario.CriarUsuarioDTO;
import com.cronos.api.dto.Usuario.LoginUsuarioDTO;
import com.cronos.api.dto.Usuario.LoginResponseDTO;
import com.cronos.api.entity.Usuario;
import com.cronos.api.service.AuthorizationService;
import com.cronos.api.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Faz o login de usuário")
    public ResponseEntity loginController(@RequestBody LoginUsuarioDTO usuarioInput) {
        var matriculaPassword = new UsernamePasswordAuthenticationToken(usuarioInput.getMatricula(),
                usuarioInput.getSenha());
        var auth = this.authenticationManager.authenticate(matriculaPassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    @Operation(summary = "Registra um novo usuário")
    public ResponseEntity<Object> registrarUsuarioController(@RequestBody CriarUsuarioDTO usuarioInput) {
        if (authorizationService.loadUserByUsername(usuarioInput.getMatricula()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPass = new BCryptPasswordEncoder().encode(usuarioInput.getSenha());
        usuarioInput.setSenha(encryptedPass);
        Usuario newUsuario = usuarioService.criarUsuario(usuarioInput);

        return ResponseEntity.ok().body(newUsuario);
    }

}
