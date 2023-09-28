package com.cronos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.cronos.api.entity.Usuario;
import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    List<Usuario> findByEmail(String email);

    List<Usuario> findByRoleEquals(String role);

    List<Usuario> findByMatricula(String matricula);

    @Query(value = "SELECT user FROM Usuario user WHERE user.matricula = :matricula")
    UserDetails findByLogin(@Param("matricula") String matricula);
}
