import { useContext } from 'react';
import { Tarefa } from '../../types/tarefasType'
import './index.css'
import { AuthContext } from '../../contexts/AuthContext';
import { Laboratorio } from '../../types/laboratorioType';
import { LaboratoriosContext } from '../../contexts/LaboratoriosContext';

type Props = {
    tableData: Tarefa[],
    laboratorio: Laboratorio,
    setLaboratorio: (laboratorio: Laboratorio) => void,
    setShowEditMenu: () => void,
    setTarefaToEdit: (tarefa: Tarefa) => void,
    cancelHandleFunction: () => void,
    refreshLabPage: () => void,
}

export const formatDataToShow = (data?: Date | string): string => {
    if(data == undefined || data == '') {
        return '';
    }
    const dataString = data + '';
    const dataFormatada = dataString.slice(0, 10).split("-");
    const dia = dataFormatada[2];
    const mes = dataFormatada[1];
    const ano = dataFormatada[0];

    return `${dia}/${mes}/${ano}`;
}

export const TableTarefas = (props: Props) => {
    const {laboratorios, setLaboratorios} = useContext(LaboratoriosContext);
    const {authToken} = useContext(AuthContext);

    const setTarefaToEditData = (tarefa: Tarefa) => {
        props.setTarefaToEdit(tarefa);
        props.setShowEditMenu();
    }

    const handleDelete = (tarefaToDelete: Tarefa) => {
         fetch(`http://localhost:8444/tarefas/delete/${tarefaToDelete.id}`, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${authToken}`
            },
            }).then().then(() => {
                const newLab = {...props.laboratorio};
                const newTarefas = newLab.tarefas.filter(tarefa => tarefa.id != tarefaToDelete.id);
                newLab.tarefas = newTarefas;
                const newContext = [...laboratorios]

                let obj = newContext.find((lab, i) => {
                    if (lab.id === newLab.id) {
                        newContext[i] = newLab;
                        return true;
                    }
                });

                setLaboratorios(newContext);
                props.setLaboratorio(newLab);

                props.refreshLabPage();
            })
        
    } 

    return(
        <table className='table' cellSpacing={0}>
            <tr className='tableHeader'>
                <th>Código</th>
                <th>Descrição da atividade</th>
                <th>Data início</th>
                <th>Data fim</th>
                <th>Carga horária</th>
                <th></th>
            </tr>
            {
                props.tableData.map((tarefa, index) => {
                    return(
                        <tr className='tableRow' key={tarefa.id}>
                            <td>{index + 1}</td>
                            <td>{tarefa.descricao}</td>
                            <td>{formatDataToShow(tarefa.dataInicio)}</td>
                            <td>{formatDataToShow(tarefa.dataFim)}</td>
                            <td>{tarefa.cargaHoraria}</td>
                            <td>
                                <span className="material-symbols-outlined actionButton" onClick={e => setTarefaToEditData(tarefa)}>edit</span>
                                <span className="material-symbols-outlined actionButton margin-left-1em" onClick={e => handleDelete(tarefa)}>delete</span>
                            </td>
                        </tr>
                    )
                })
            }
        </table>
    )
}