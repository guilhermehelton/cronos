/* eslint-disable @typescript-eslint/no-unused-vars */
import { useParams } from "react-router-dom"
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"
import { useContext } from "react"
import { LaboratoriosContext } from "../../contexts/PaginaContext"
import { labType } from "../../types/labListType"

export const Laboratorio = () => {
    const {laboratorioId} = useParams();
    const {laboratorios} = useContext(LaboratoriosContext);

    const getLaboratorioData = () : labType => {
        return laboratorios.filter(lab => lab.id == laboratorioId)[0];
    }

    return (
        <div className="laboratorio">
            <Sidebar />
            <Header tittle={getLaboratorioData().name} icon='groups'/>
        </div>
    )
}