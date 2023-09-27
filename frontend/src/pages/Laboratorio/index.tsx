/* eslint-disable @typescript-eslint/no-unused-vars */
import { useParams } from "react-router-dom"
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"
import { useContext } from "react"
import { LaboratoriosContext } from "../../contexts/LaboratorioContext"
import { labType } from "../../types/labListType"
import { FloatCard } from "../../components/floatCard"
import { TopBar } from "../../components/topBar"
import { TableTarefas } from "../../components/table/TableTarefas"
import { AddButton } from "../../components/addButton"
import { Tarefa } from "../../types/typeTarefa"

export const Laboratorio = () => {
    const {laboratorioId} = useParams();
    const {laboratorios} = useContext(LaboratoriosContext);

    const getLaboratorioData = () : labType => {
        return laboratorios.filter(lab => lab.id == laboratorioId)[0];
    }

    const tarefasList : Tarefa[] = [{
        id: '01',
        descricao: 'Tarefa 01',
        dataInicio: '29/09/2023',
        dataFim: '30/09/2023',
        cargaHoraria: '5 horas',
    }, {
        id: '02',
        descricao: 'Tarefa 02',
        dataInicio: '29/09/2023',
        dataFim: '30/09/2023',
        cargaHoraria: '5 horas'
    }, {
        id: '03',
        descricao: 'Tarefa 03',
        dataInicio: '29/09/2023',
        dataFim: '30/09/2023',
        cargaHoraria: '5 horas'
    }]

    return (
        <div className="laboratorio">
            <Sidebar />
            <div className="bodyPage">
                <Header tittle={getLaboratorioData().name} icon='groups'/>
                <div className="cards"> 
                    <FloatCard titulo="Atividades" icon="description" info="03"/>
                    <FloatCard titulo="Membros" icon="groups" info="03" isSecond={true}/>
                </div>
                <TopBar />
                <div className="tableDiv">
                    <AddButton />
                    <TableTarefas tableData={tarefasList}/>
                </div>
            </div>
        </div>
    )
}