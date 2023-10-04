import { useNavigate } from "react-router-dom"
import { NavButton } from "../navButton"
import "./index.css"
import { useContext } from "react";
import { AuthContext } from "../../contexts/AuthContext";
import { Usuario } from "../../types/usuarioType";
import { LaboratoriosContext } from "../../contexts/LaboratoriosContext";
import { RoleEnum } from "../../types/roleEnum";

export const Sidebar = () => {
    const navigate = useNavigate();
    const {usuario, setUsuario, setSigned} = useContext(AuthContext);
    const {laboratorios} = useContext(LaboratoriosContext);

    const handleSair = () => {
        sessionStorage.removeItem("@Auth:token")
        sessionStorage.removeItem("@Auth:user")
        setSigned(false);
        setUsuario({} as Usuario);
        
        navigate("/");
    }

    const handleNavegarPaginaPrincipal = () => {
        navigate("/home")
    }

    const handleNavegarCoordenador = () => {
        navigate("/coordenador")
    }

    return (
        <div className="sidebar">
            {/* Foto de usuário e nome*/}
            <div className="userData">
                <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" viewBox="0 0 60 60" fill="none">
                    <g clip-path="url(#clip0_3_52)">
                        <rect width="60" height="60" rx="30" fill="white"/>
                        <rect x="18" y="10" width="24" height="24" rx="12" fill="#4ACFAC"/>
                        <rect x="-15" y="40" width="90" height="90" rx="45" fill="#4ACFAC"/>
                    </g>
                    <defs>
                        <clipPath id="clip0_3_52">
                            <rect width="60" height="60" rx="30" fill="white"/>
                        </clipPath>
                    </defs>
                </svg>
                <p>{usuario.nome}</p>
            </div>

            {/* Botoes de navegação */}
            <NavButton label="Principal" icon="home" handleFunction={handleNavegarPaginaPrincipal}/>
            <NavButton label="Laboratórios" isDropdown={true} icon="groups" itemList={laboratorios}/>
            {(usuario.role == RoleEnum.COORDENADOR) && 
                <NavButton label="Coordenador" icon="person_add" handleFunction={handleNavegarCoordenador}/>
            }
            <NavButton label="Sair" icon="logout" handleFunction={handleSair}/>
        </div>
    )
}