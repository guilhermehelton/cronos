package com.cronos.api.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronos.api.dto.CriarLaboratorioDTO;
import com.cronos.api.entity.Laboratorio;
import com.cronos.api.entity.Usuario;
import com.cronos.api.repository.LaboratorioRepository;
import com.cronos.api.repository.UsuarioRepository;

@Service
public class LaboratorioService {
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Laboratorio criarLaboratorio(CriarLaboratorioDTO laboratorioInput) {
        Set<Usuario> listaAlunos = new HashSet<Usuario>();
        for (String matricula : laboratorioInput.getListaMatriculaAlunos()) {
            Usuario membro = usuarioRepository.findByMatricula(matricula).get(0);

            listaAlunos.add(membro);
        }

        Optional<Usuario> coordenador = usuarioRepository
                .findById(UUID.fromString(laboratorioInput.getIdCoordenador()));

        if (!coordenador.isPresent()) {
            return null;
        }

        Laboratorio newLaboratorio = new Laboratorio();
        newLaboratorio.setIdCoordenador(coordenador.get().getId());
        newLaboratorio.setNome(laboratorioInput.getNome());
        newLaboratorio.setNumeroSala(laboratorioInput.getNumeroSala());
        newLaboratorio.setEquipe(listaAlunos);

        Laboratorio savedLaboratorio = laboratorioRepository.save(newLaboratorio);

        return savedLaboratorio;
    }

    public Laboratorio listarLaboratorio(UUID idLaboratorio) {
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(idLaboratorio);

        if (laboratorio.isPresent()) {
            return laboratorio.get();
        } else {
            return null;
        }
    }

    public List<Laboratorio> listarTodosLaboratorios() {
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();

        return laboratorios;
    }
}
