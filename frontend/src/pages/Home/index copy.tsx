/* eslint-disable @typescript-eslint/no-unused-vars */
import { useContext } from "react"
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"
import { PaginaContext } from "../../contexts/LaboratorioContext"

export const Home = () => {
    const {paginaAtual, tituloPagina, icon} = useContext(PaginaContext);

    return (
        <div className="home">
            <Sidebar />
            <Header tittle={tituloPagina} icon={icon == 'home' ? 'home' : 'groups'}/>
        </div>
    )
}