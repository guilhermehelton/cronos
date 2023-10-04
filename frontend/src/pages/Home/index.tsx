/* eslint-disable @typescript-eslint/no-unused-vars */
import { useContext, useEffect } from "react"
import { Header } from "../../components/header"
import { Sidebar } from "../../components/sidebar"
import "./index.css"
import { LaboratoriosContext } from "../../contexts/LaboratoriosContext"
import { AuthContext } from "../../contexts/AuthContext"

export const Home = () => {
    const {setLaboratorios} = useContext(LaboratoriosContext);
    const {usuario, authToken} = useContext(AuthContext);
    
    useEffect(() => {
        fetch(`http://localhost:8444/laboratorio/listar-por-membro/${usuario.id}`, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${authToken}`
            },
            }).then((response) => response.json()).then((data) => {
                setLaboratorios(data);
            })
    }, [])

    return (
        <div className="home">
            <Sidebar />
            <div className="bodyPage">
                <Header tittle='Dados do Usuário' icon='home'/>
            </div>
        </div>
    )
}