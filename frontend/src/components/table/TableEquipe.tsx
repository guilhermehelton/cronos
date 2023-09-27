import { MembroEquipe } from '../../types/typeMembroEquipe'
import './index.css'

type Props = {
    tableData: MembroEquipe[]
}

export const TableEquipe = (props: Props) => {
    return(
        <table className='table' cellSpacing={0}>
            <tr className='tableHeader'>
                <th>Matrícula</th>
                <th>Nome</th>
                <th>Data de nascimento</th>
                <th>Email</th>
                <th>Carga horária realizada</th>
            </tr>
            {
                props.tableData.map(membro => {
                    return(
                        <tr className='tableRow'>
                            <td>{membro.matricula}</td>
                            <td>{membro.nome}</td>
                            <td>{membro.dataNascimento}</td>
                            <td>{membro.email}</td>
                            <td>{membro.cargaHorariaRealizada}</td>
                        </tr>
                    )
                })
            }
        </table>
    )
}