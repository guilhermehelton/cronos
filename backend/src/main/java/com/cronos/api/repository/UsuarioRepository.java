package com.cronos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronos.api.entity.Usuario;
import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    List<Usuario> findByEmail(String email);

    List<Usuario> findByTipoPerfilEquals(String tipoPerfil);

    List<Usuario> findByMatricula(String matricula);
}
