/* eslint-disable @typescript-eslint/no-unused-vars */
import { useParams } from "react-router-dom"
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"
import { useContext, useEffect, useState } from "react"
import { FloatCard } from "../../components/floatCard"
import { TopBar } from "../../components/topBar"
import { TableTarefas } from "../../components/table/TableTarefas"
import { AddButton } from "../../components/addButton"
import { Laboratorio } from "../../types/laboratorioType"
import { LaboratoriosContext } from "../../contexts/LaboratoriosContext"
import { CadastroAtividade } from "../CadastroAtividade"
import { Tarefa } from "../../types/tarefasType"

export const LaboratorioPage = () => {
    const {laboratorioId} = useParams();
    const {laboratorios} = useContext(LaboratoriosContext);
    const [laboratorio, setLaboratorio] = useState({} as Laboratorio);
    const [isCadastro, setIsCadastro] = useState(false);
    const [isUpdate, setIsUpdate] = useState(false);
    const [tarefaToEdit, setTarefaToEdit] = useState({} as Tarefa);
    const [refresh, setRefresh] = useState(false);

    useEffect(() => {
        setLaboratorio(laboratorios.filter(lab => lab.id == laboratorioId)[0]);
    }, [])

    const handleChangeCadastro = () => {
        if(isCadastro){
            setIsCadastro(false);
            setTarefaToEdit({} as Tarefa);
        } else{
            setIsCadastro(true);
        }
    }

    const handleShowEditMenu = () => {
        setIsCadastro(true);
        setIsUpdate(true);
    }

    const refreshPage = () => {
        if(refresh) {
            setRefresh(false);
        } else {
            setRefresh(true);
        }
    }

    return (
        (laboratorio.tarefas && laboratorio.equipe) &&
        <div className="laboratorio">
            <Sidebar />
            <div className="bodyPage">
                <Header tittle={laboratorio.nome} icon='groups'/>
                <div className="cards"> 
                    <FloatCard titulo="Atividades" icon="description" info={laboratorio.tarefas.length + ''}/>
                    <FloatCard titulo="Membros" icon="groups" info={laboratorio.equipe.length + ''} isSecond={true}/>
                </div>
                <TopBar />
                <div className="tableDiv">
                    {
                    isCadastro ? 
                        <>
                            <CadastroAtividade
                                    tarefa={tarefaToEdit}
                                    isUpdate={isUpdate}
                                    laboratorio={laboratorio}
                                    cancelHandleFunction={handleChangeCadastro}
                                    setLaboratorio={setLaboratorio}/>
                        </>
                        : 
                        <>
                            <AddButton handleFunction={handleChangeCadastro}/>
                            <TableTarefas
                                refreshLabPage={refreshPage}
                                laboratorio={laboratorio}
                                setLaboratorio={setLaboratorio}
                                cancelHandleFunction={handleChangeCadastro}
                                setTarefaToEdit={setTarefaToEdit}
                                setShowEditMenu={handleShowEditMenu}
                                tableData={laboratorio.tarefas ?? []}/>
                        </>
                    }
                </div>
            </div>
        </div>
    )
}