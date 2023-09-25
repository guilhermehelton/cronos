/* eslint-disable @typescript-eslint/no-unused-vars */
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"

export const Coordenador = () => {
    return (
        <div className="coordenador">
            <Sidebar />
            <Header tittle="Cadastrar Coordenador" icon='groups'/>
        </div>
    )
}