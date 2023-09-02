package com.cronos.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronos.api.entity.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, UUID> {

}
