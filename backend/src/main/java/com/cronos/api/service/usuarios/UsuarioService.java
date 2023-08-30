package com.cronos.api.service.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronos.api.dto.CriarUsuarioDTO;
import com.cronos.api.dto.EnumTipoPerfil;
import com.cronos.api.entity.Usuario;
import com.cronos.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarAluno(CriarUsuarioDTO inputUsuario) {
        List<Usuario> userExists = usuarioRepository.findByEmail(inputUsuario.getEmail());

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

        Usuario savedUser = usuarioRepository.save(newUser);

        return savedUser;
    }

    public List<Usuario> listarAlunos() {
        List<Usuario> usuarios = usuarioRepository.findByTipoPerfilEquals(EnumTipoPerfil.ALUNO.toString());

        return usuarios;
    }
}
