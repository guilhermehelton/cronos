import { useNavigate } from "react-router-dom"
import { NavButton } from "../navButton"
import "./index.css"
import { labType } from "../../types/labListType";
import { useContext } from "react";
import { PaginaContext } from "../../contexts/PaginaContext";
import { PaginasEnum } from "../../types/paginasEnum";

export const Sidebar = () => {
    const navigate = useNavigate();
    const {handleTrocaPagina} = useContext(PaginaContext);


    const labList: labType[] = [{
        id: '1',
        name: 'Laboratório 01'
    },{
        id: '2',
        name: 'Laboratório 02'
    }];

    const handleSair = () => {
        navigate("/")
    }

    const handleNavegarPaginaPrincipal = () => {
        handleTrocaPagina(PaginasEnum.HOME, 'Dados do Usuário', 'home');
    }

    const handleNavegarCoordenador = () => {
        handleTrocaPagina(PaginasEnum.COORDENADOR, 'Cadastrar Coordenador', 'groups');
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
                <p>Nome de usuário</p>
            </div>

            {/* Botoes de navegação */}
            <NavButton label="Principal" icon="home" handleFunction={handleNavegarPaginaPrincipal}/>
            <NavButton label="Laboratórios" isDropdown={true} icon="groups" itemList={labList}/>
            <NavButton label="Coordenador" icon="person_add" handleFunction={handleNavegarCoordenador}/>
            <NavButton label="Sair" icon="logout" handleFunction={handleSair}/>
        </div>
    )
}