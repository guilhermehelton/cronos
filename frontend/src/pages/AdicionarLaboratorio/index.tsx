import { Input } from "../../components/formInput/input"
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import { TableEquipe } from "../../components/table/TableEquipe"
import { MembroEquipe } from "../../types/typeMembroEquipe"
import "./index.css"

export const AdicionarLaboratorio = () => {
    const equipeList : MembroEquipe[] = [{
        matricula: '011111',
        nome: 'João',
        dataNascimento: '29/09/2001',
        email: '30/09/2023',
        cargaHorariaRealizada: '5 horas',
    }, {
        matricula: '022222',
        nome: 'Maria',
        dataNascimento: '29/09/2003',
        email: '30/09/2023',
        cargaHorariaRealizada: '6 horas'
    }]


    return (
        <div className="add-laboratorio">
            <Sidebar />
            <div className="bodyPage">
                <Header tittle="Cadastrar Laboratório" icon='groups'/>
                <div className="addLabForm">
                    <div className="form-2-col lab">
                        <Input inputType="text" icon="badge" inputName="labName" labelName="Nome"/>
                        <Input inputType="text" icon="location_on" inputName="labName" labelName="Número da Sala" aditionalClassName="margin-left"/>
                    </div>
                    <div className="form-buttons lab">
                        <button type="button" className="secondary-btn lab">Adicionar membro</button>
                        <button type="button" className="primary-btn">Confirmar</button>
                    </div>
                </div>
                <div className="tableDiv">
                    <span className="align-self-left">Lista de membros:</span>
                    <TableEquipe tableData={equipeList}/>
                </div>
            </div>
        </div>
    )
}