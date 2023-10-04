import { useContext, useState } from "react"
import { Input } from "../../components/formInput/input"
import "./index.css"
import { AuthContext } from "../../contexts/AuthContext"
import { Laboratorio } from "../../types/laboratorioType"
import { Tarefa } from "../../types/tarefasType"
import { formatDataToShow } from "../../components/table/TableTarefas"

type Props = {
    cancelHandleFunction: () => void,
    laboratorio: Laboratorio,
    setLaboratorio: (laboratorio: Laboratorio) => void,
    isUpdate?: boolean,
    tarefa: Tarefa,
}

export const CadastroAtividade = (props: Props) => {
    const [descricao, setDescricao] = useState(props.tarefa.descricao ? props.tarefa.descricao + '': '');
    const [cargaHoraria, setCargaHoraria] = useState(props.tarefa.cargaHoraria ? props.tarefa.cargaHoraria + '': '');
    const [dataInicio, setDataInicio] = useState(props.tarefa.dataInicio ? formatDataToShow(props.tarefa.dataInicio): '');
    const [dataFim, setDataFim] = useState(props.tarefa.dataFim ? formatDataToShow(props.tarefa.dataFim): '');
    const {usuario, authToken} = useContext(AuthContext);

    const formataData = (match: RegExpMatchArray) => {
        const dia = parseInt(match[1], 10);
        const mes = parseInt(match[2], 10) - 1;
        const ano = parseInt(match[3], 10);
            
        const data = new Date(ano, mes, dia);
            
        return data.toISOString();
    }

    const dataValida = (data: string) => {
        const regex = /^(\d{2})\/(\d{2})\/(\d{4})$/;
  
        const match = data.match(regex);
  
        if (match) {
            return match
        } else {
            alert("Data no formato invalido. Tente dd/MM/yyyy")
        }
    }

    const handleConfirmCreate = () =>{
        const isDataInicioValid = dataValida(dataInicio);
        const isDataFimValid = dataValida(dataFim);
        if(isDataInicioValid && isDataFimValid){
            const tarefa = {
                descricao: descricao,
                dataInicio: formataData(isDataInicioValid),
                dataFim: formataData(isDataFimValid),
                cargaHoraria: parseInt(cargaHoraria),
                idDono: usuario.id,
                idLaboratorio: props.laboratorio.id
            }
    
            fetch('http://localhost:8444/tarefas', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${authToken}`
                },
                body: JSON.stringify(tarefa),
                }).then((response) => response.json()).then((data) => {
                    const newLab = {...props.laboratorio};
                    newLab.tarefas.push({
                        ...data
                    })
                    props.setLaboratorio(newLab);

                    props.cancelHandleFunction();
                })
        }
    }

    const handleConfirmUpdate = () => {
        const isDataInicioValid = dataValida(dataInicio);
        const isDataFimValid = dataValida(dataFim);
        if(isDataInicioValid && isDataFimValid){
            const tarefa = {
                descricao: descricao,
                dataInicio: formataData(isDataInicioValid),
                dataFim: formataData(isDataFimValid),
                cargaHoraria: cargaHoraria,
            }
    
            if(props.tarefa.id){
                fetch(`http://localhost:8444/tarefas/${props.tarefa.id}`, {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${authToken}`
                },
                body: JSON.stringify(tarefa),
                }).then((response) => response.json()).then(() => {
                    const newLab = {...props.laboratorio};
                    const tarefaIndex = newLab.tarefas.findIndex((tarefa => tarefa.id == props.tarefa?.id))
                    newLab.tarefas[tarefaIndex].descricao = tarefa.descricao;
                    newLab.tarefas[tarefaIndex].cargaHoraria = parseInt(tarefa.cargaHoraria);
                    newLab.tarefas[tarefaIndex].dataInicio = tarefa.dataInicio;
                    newLab.tarefas[tarefaIndex].dataFim = tarefa.dataFim;

                    props.cancelHandleFunction();
                })
            }
        }
    }

    return(
        props.isUpdate ?
        <div className="atividadeForm">
            <div className="form-2-col margin-top-2em">
                    <Input value={descricao}
                        inputType="text" icon="description"
                        inputName="descricao" labelName="Descricao"
                        aditionalClassName="margin-right"
                        handleFunction={e => setDescricao(e.target.value)}/>
                    <Input value={cargaHoraria}
                        inputType="text"
                        icon="timer"
                        inputName="cargaHoraria"
                        labelName="Carga horária"
                        handleFunction={e => setCargaHoraria(e.target.value)}/>
                </div>
                <div className="form-2-col margin-top-2em">
                    <Input value={dataInicio} 
                        inputType="text" icon="calendar_today" inputName="dtInicio"
                        labelName="Data início"
                        aditionalClassName="margin-right"
                        handleFunction={e => setDataInicio(e.target.value)}/>
                    <Input value={dataFim}
                        inputType="text"
                        icon="calendar_today"
                        inputName="dtFim"
                        labelName="Data fim"
                        handleFunction={e => setDataFim(e.target.value)}/>
                </div>
                <div className="form-buttons align-self-right margin-top-2em">
                    <button type="button" className="secondary-btn" onClick={props.cancelHandleFunction}>Cancelar</button>
                    <button type="button" className="primary-btn" onClick={handleConfirmUpdate}>Confirmar</button>
                </div>
        </div>
        :
        <div className="atividadeForm">
            <div className="form-2-col margin-top-2em">
                    <Input inputType="text"
                        icon="description"
                        inputName="descricao"
                        labelName="Descricao"
                        aditionalClassName="margin-right"
                        handleFunction={e => setDescricao(e.target.value)}/>
                    <Input inputType="text"
                        icon="timer"
                        inputName="cargaHoraria"
                        labelName="Carga horária"
                        handleFunction={e => setCargaHoraria(e.target.value)}/>
                </div>
                <div className="form-2-col margin-top-2em">
                    <Input inputType="text"
                        icon="calendar_today"
                        inputName="dtInicio"
                        labelName="Data início"
                        aditionalClassName="margin-right"
                        handleFunction={e => setDataInicio(e.target.value)}/>
                    <Input inputType="text"
                        icon="calendar_today"
                        inputName="dtFim"
                        labelName="Data fim"
                        handleFunction={e => setDataFim(e.target.value)}/>
                </div>
                <div className="form-buttons align-self-right margin-top-2em">
                    <button type="button" className="secondary-btn" onClick={props.cancelHandleFunction}>Cancelar</button>
                    <button type="button" className="primary-btn" onClick={handleConfirmCreate}>Confirmar</button>
                </div>
        </div>
    )
}