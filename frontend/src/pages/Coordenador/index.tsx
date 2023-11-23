import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"

export const Coordenador = () => {
    return (
        <div className="coordenador">
            <Sidebar />
            <div className="bodyPage">
                <Header tittle="Cadastrar Coordenador" icon='groups'/>
            </div>
        </div>
    )
}