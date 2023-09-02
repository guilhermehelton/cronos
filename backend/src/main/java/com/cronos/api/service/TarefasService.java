package com.cronos.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronos.api.dto.CriarTarefaDTO;
import com.cronos.api.entity.Laboratorio;
import com.cronos.api.entity.Tarefa;
import com.cronos.api.entity.Usuario;
import com.cronos.api.repository.LaboratorioRepository;
import com.cronos.api.repository.TarefasRepository;
import com.cronos.api.repository.UsuarioRepository;

@Service
public class TarefasService {
    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public Tarefa criarTarefa(CriarTarefaDTO tarefaInput) {
        Optional<Usuario> donoTarefa = usuarioRepository.findById(UUID.fromString(tarefaInput.getIdDono()));

        if (!donoTarefa.isPresent()) {
            return null;
        }

        Optional<Laboratorio> laboratorio = laboratorioRepository
                .findById(UUID.fromString(tarefaInput.getIdLaboratorio()));

        if (!laboratorio.isPresent()) {
            return null;
        }

        Tarefa newTarefa = new Tarefa();
        newTarefa.setDescricao(tarefaInput.getDescricao());
        newTarefa.setDataInicio(tarefaInput.getDataInicio());
        newTarefa.setDataFim(tarefaInput.getDataFim());
        newTarefa.setCargaHoraria(tarefaInput.getCargaHoraria());
        newTarefa.setIdDono(donoTarefa.get().getId());
        newTarefa.setLaboratorio(laboratorio.get());

        Tarefa savedTarefa = tarefasRepository.save(newTarefa);

        // Adiciona carga horária da atividade no total do usuário
        int novaCargaHorariaTotal = tarefaInput.getCargaHoraria() + donoTarefa.get().getCargaHorariaTotal();

        donoTarefa.get().setCargaHorariaTotal(novaCargaHorariaTotal);

        usuarioRepository.save(donoTarefa.get());

        return savedTarefa;
    }
}
