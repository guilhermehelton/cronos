/* eslint-disable @typescript-eslint/no-unused-vars */
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"

export const AdicionarLaboratorio = () => {
    return (
        <div className="add-laboratorio">
            <Sidebar />
            <Header tittle='Adicionar Laboratório' icon='groups'/>
        </div>
    )
}