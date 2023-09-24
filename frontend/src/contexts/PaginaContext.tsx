import { ReactNode, createContext, useState } from "react";
import { PaginasEnum } from "../types/paginasEnum";

type Props = {
    children: ReactNode
}

type PaginaContextType = {
    paginaAtual: PaginasEnum,
    handleTrocaPagina: (newPagina: PaginasEnum, tituloPagina: string, iconPagina: string) => void,
    tituloPagina: string
    icon: string,
}

const initialValue = {
    paginaAtual: PaginasEnum.HOME,
    handleTrocaPagina: () => {},
    tituloPagina: 'Dados do Usuário',
    icon: 'home'
}

export const PaginaContext = createContext<PaginaContextType>(initialValue);

export const PaginaContextProvider = ({children} : Props) => {
    const [paginaAtual, setPaginaAtual] = useState(initialValue.paginaAtual);
    const [tituloPagina, setTituloPagina] = useState(initialValue.tituloPagina);
    const [icon, setIconPagina] = useState(initialValue.icon);

    const handleTrocaPagina = (newPagina: PaginasEnum, tituloPagina: string, iconPagina: string) => {
        setPaginaAtual(newPagina);
        setTituloPagina(tituloPagina);
        setIconPagina(iconPagina);
    }

    return (
        <PaginaContext.Provider value={{paginaAtual, handleTrocaPagina, tituloPagina, icon}}>
            {children}
        </PaginaContext.Provider>
    )
}