import { ReactNode, createContext, useState } from "react";
import { Laboratorio } from "../types/laboratorioType";

type Props = {
    children: ReactNode
}

type AuthContextType = {
    laboratorios: Laboratorio[],
    setLaboratorios: (laboratorios: Laboratorio[]) => void,
}

const initialValue = {
    laboratorios: [] as Laboratorio[],
    setLaboratorios: () => {},
}

export const LaboratoriosContext = createContext<AuthContextType>(initialValue);

export const LaboratoriosContextProvider = ({children} : Props) => {
    const [laboratorios, setLaboratorios] = useState(initialValue.laboratorios);

    return (
        <LaboratoriosContext.Provider value={{laboratorios, setLaboratorios}}>
            {children}
        </LaboratoriosContext.Provider>
    )
}