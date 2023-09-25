import { BrowserRouter, Route, Routes } from "react-router-dom"
import { Login } from "../pages/Login"
import { Cadastro } from "../pages/CadastroAluno"
import { Home } from "../pages/Home"
import { Coordenador } from "../pages/Coordenador"
import { Laboratorio } from "../pages/Laboratorio"
import { AdicionarLaboratorio } from "../pages/AdicionarLaboratorio"

export const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login/>}></Route>
                <Route path="/cadastro" element={<Cadastro/>}></Route>
                <Route path="/home" element={<Home/>}></Route>
                <Route path="/laboratorio" element={<Laboratorio/>}>
                    <Route path=":laboratorioId" element={<Laboratorio/>}></Route>
                </Route>
                <Route path="/laboratorio/add" element={<AdicionarLaboratorio/>}></Route>
                <Route path="/coordenador" element={<Coordenador/>}></Route>
            </Routes>
        </BrowserRouter>
    )
}