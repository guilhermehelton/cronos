import { Tarefa } from "./tarefasType"

export type Laboratorio = {
    id: string,
    nome: string,
    numeroSala: 0,
    idCoordenador: string,
    equipe: string[],
    tarefas: Tarefa[]
}