package com.cronos.api.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronos.api.dto.Tarefa.CriarTarefaDTO;
import com.cronos.api.dto.Tarefa.EditarTareafDTO;
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

        Tarefa newTarefa = Tarefa.builder()
                .descricao(tarefaInput.getDescricao())
                .dataInicio(tarefaInput.getDataInicio())
                .dataFim(tarefaInput.getDataFim())
                .cargaHoraria(tarefaInput.getCargaHoraria())
                .idDono(donoTarefa.get().getId())
                .laboratorio(laboratorio.get())
                .build();

        Tarefa savedTarefa = tarefasRepository.save(newTarefa);

        // Adiciona carga horária da atividade no total do usuário
        int novaCargaHorariaTotal = tarefaInput.getCargaHoraria() + donoTarefa.get().getCargaHorariaTotal();

        donoTarefa.get().setCargaHorariaTotal(novaCargaHorariaTotal);

        usuarioRepository.save(donoTarefa.get());

        return savedTarefa;
    }

    public Tarefa editarTarefa(UUID idTarefa, EditarTareafDTO inputEditarTarefaDto) {
        Optional<Tarefa> tarefa = tarefasRepository.findById(idTarefa);

        if (!tarefa.isPresent()) {
            return null;
        }

        int diferencaCargaHoraria = inputEditarTarefaDto.getCargaHoraria() - tarefa.get().getCargaHoraria();

        tarefa.get().setDescricao(inputEditarTarefaDto.getDescricao());
        tarefa.get().setCargaHoraria(inputEditarTarefaDto.getCargaHoraria());
        tarefa.get().setDataInicio(inputEditarTarefaDto.getDataInicio());
        tarefa.get().setDataFim(inputEditarTarefaDto.getDataFim());

        Optional<Usuario> donoTarefa = usuarioRepository.findById(tarefa.get().getIdDono());

        donoTarefa.get().setCargaHorariaTotal(donoTarefa.get().getCargaHorariaTotal() + diferencaCargaHoraria);

        usuarioRepository.save(donoTarefa.get());

        return tarefasRepository.save(tarefa.get());

    }

    public void deletarTarefa(UUID idTarefa) {
        Optional<Tarefa> tarefa = tarefasRepository.findById(idTarefa);

        if (tarefa.isPresent()) {
            Optional<Laboratorio> laboratorio = laboratorioRepository.findById(tarefa.get().getLaboratorio().getId());

            Set<Tarefa> novaListaTarefas = laboratorio.get().getTarefas();

            novaListaTarefas.remove(tarefa.get());

            laboratorio.get().setTarefas(novaListaTarefas);

            laboratorioRepository.save(laboratorio.get());

            tarefasRepository.deleteById(tarefa.get().getId());
        }
    }
}
