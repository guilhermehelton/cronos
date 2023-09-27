import { Tarefa } from '../../types/typeTarefa'
import './index.css'

type Props = {
    tableData: Tarefa[]
}

export const TableTarefas = (props: Props) => {
    return(
        <table className='table' cellSpacing={0}>
            <tr className='tableHeader'>
                <th>Código</th>
                <th>Descrição</th>
                <th>Data início</th>
                <th>Data fim</th>
                <th>Carga horária</th>
            </tr>
            {
                props.tableData.map(tarefa => {
                    return(
                        <tr className='tableRow'>
                            <td>{tarefa.id}</td>
                            <td>{tarefa.descricao}</td>
                            <td>{tarefa.dataInicio}</td>
                            <td>{tarefa.dataFim}</td>
                            <td>{tarefa.cargaHoraria}</td>
                        </tr>
                    )
                })
            }
        </table>
    )
}