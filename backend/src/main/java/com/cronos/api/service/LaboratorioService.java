package com.cronos.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronos.api.dto.Laboratorio.CriarLaboratorioDTO;
import com.cronos.api.entity.Laboratorio;
import com.cronos.api.entity.Tarefa;
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

        listaAlunos.add(coordenador.get());

        Laboratorio newLaboratorio = Laboratorio.builder()
                .idCoordenador(coordenador.get().getId())
                .nome(laboratorioInput.getNome())
                .numeroSala(laboratorioInput.getNumeroSala())
                .equipe(listaAlunos)
                .build();

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

    public List<Laboratorio> listarLaboratorioByMembro(UUID idMembro) {
        List<Laboratorio> laboratorios = laboratorioRepository.findLaboratorioByUsuarioId(idMembro);

        return laboratorios;
    }

    public List<Usuario> listarMembrosLaboratorio(UUID idLaboratorio) {
        List<Usuario> labUsuarios = laboratorioRepository.findAllMembrosLaboratorio(idLaboratorio);

        return labUsuarios;
    }

    public List<Tarefa> listarTarefasLaboratorio(UUID idLaboratorio) {
        List<Tarefa> labTarefas = laboratorioRepository.findAllTarefasLaboratorio(idLaboratorio);

        return labTarefas;
    }

    public Usuario addMembroLaboratorio(String matricula, UUID idLaboratorio) {
        Usuario newMembro = usuarioRepository.findByMatricula(matricula).get(0);
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(idLaboratorio);

        if (newMembro == null || !laboratorio.isPresent()) {
            return null;
        }

        Set<Usuario> novaListaMembros = laboratorio.get().getEquipe();

        novaListaMembros.add(newMembro);

        laboratorio.get().setEquipe(novaListaMembros);

        laboratorioRepository.save(laboratorio.get());

        return newMembro;
    }

    public Usuario removerMembroLaboratorio(String matricula, UUID idLaboratorio) {
        Usuario membro = usuarioRepository.findByMatricula(matricula).get(0);
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(idLaboratorio);

        if (membro == null || !laboratorio.isPresent()) {
            return null;
        }

        Set<Usuario> novaListaMembros = laboratorio.get().getEquipe();

        novaListaMembros.remove(membro);

        laboratorio.get().setEquipe(novaListaMembros);

        laboratorioRepository.save(laboratorio.get());

        return membro;
    }
}
