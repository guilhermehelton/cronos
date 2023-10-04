import { BrowserRouter, Route, Routes } from "react-router-dom"
import { Login } from "../pages/Login"
import { Cadastro } from "../pages/CadastroAluno"
import { Home } from "../pages/Home"
import { Coordenador } from "../pages/Coordenador"
import { LaboratorioPage } from "../pages/Laboratorio"
import { AdicionarLaboratorio } from "../pages/AdicionarLaboratorio"
import { PrivateRoutes } from "."

export const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login/>}></Route>
                <Route path="/cadastro" element={<Cadastro/>}></Route>
                <Route path="/home" element={<PrivateRoutes/>}>
                    <Route path="/home" element={<Home/>}/>
                </Route>
                <Route path="/laboratorio" element={<PrivateRoutes/>}>
                    <Route path="/laboratorio" element={<LaboratorioPage/>}>
                        <Route path=":laboratorioId" element={<LaboratorioPage/>}></Route>
                    </Route>
                </Route>
                <Route path="/laboratorio/add" element={<PrivateRoutes/>}>
                    <Route path="/laboratorio/add" element={<AdicionarLaboratorio/>}></Route>
                </Route>
                <Route path="/coordenador" element={<PrivateRoutes/>}>
                    <Route path="/coordenador" element={<Coordenador/>}></Route>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}