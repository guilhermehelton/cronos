package com.cronos.api.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cronos.api.dto.EnumTipoPerfil;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String email;

    @Column
    private Date dataNascimento;

    @Column(length = 64, nullable = false)
    private String senha;

    @Column(length = 64, nullable = true)
    private String curso;

    @Column(length = 14, nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private EnumTipoPerfil role;

    @Column(nullable = true)
    private Integer cargaHorariaTotal;

    @JsonBackReference
    @ManyToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    private Set<Laboratorio> laboratorios;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == EnumTipoPerfil.COORDENADOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_COORDENADOR"), new SimpleGrantedAuthority("ROLE_ALUNO"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
        }

    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return matricula;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
