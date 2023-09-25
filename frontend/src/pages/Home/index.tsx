/* eslint-disable @typescript-eslint/no-unused-vars */
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"

export const Home = () => {
    return (
        <div className="home">
            <Sidebar />
            <Header tittle='Dados do Usuário' icon='home'/>
        </div>
    )
}