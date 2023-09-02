package com.cronos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronos.api.dto.EnumTipoPerfil;
import com.cronos.api.dto.Usuario.CriarUsuarioDTO;
import com.cronos.api.dto.Usuario.EditarUsuarioDTO;
import com.cronos.api.entity.Usuario;
import com.cronos.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(CriarUsuarioDTO inputUsuario) {
        List<Usuario> userExists = usuarioRepository.findByMatricula(inputUsuario.getMatricula());

        if (!userExists.isEmpty()) {
            return null;
        }

        Usuario newUser = new Usuario();
        newUser.setNome(inputUsuario.getNome());
        newUser.setEmail(inputUsuario.getEmail());
        newUser.setDataNascimento(inputUsuario.getDataNascimento());
        newUser.setCurso(inputUsuario.getCurso());
        newUser.setMatricula(inputUsuario.getMatricula());
        newUser.setSenha(inputUsuario.getSenha());
        newUser.setTipoPerfil(inputUsuario.getTipoPerfil().toString());
        newUser.setCargaHorariaTotal(0);

        Usuario savedUser = usuarioRepository.save(newUser);

        return savedUser;
    }

    public List<Usuario> listarUsuariosAlunos() {
        List<Usuario> usuarios = usuarioRepository.findByTipoPerfilEquals(EnumTipoPerfil.ALUNO.toString());

        return usuarios;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios;
    }

    public Usuario buscarPorMatricula(String matricula) {
        Usuario usuario = usuarioRepository.findByMatricula(matricula).get(0);

        return usuario;
    }

    public Usuario editarUsuario(UUID idUsuario, EditarUsuarioDTO inputEditarUsuarioDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        Usuario emailExists = usuarioRepository.findByEmail(inputEditarUsuarioDTO.getEmail()).get(0);

        if (!usuario.isPresent()) {
            return null;
        }

        if (emailExists != null) {
            return null;
        }

        usuario.get().setNome(inputEditarUsuarioDTO.getNome());
        usuario.get().setCurso(inputEditarUsuarioDTO.getCurso());
        usuario.get().setEmail(inputEditarUsuarioDTO.getEmail());

        return usuarioRepository.save(usuario.get());
    }
}
