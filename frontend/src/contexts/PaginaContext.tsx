import { ReactNode, createContext, useState } from "react";
import { labType } from "../types/labListType";

type Props = {
    children: ReactNode
}

type LaboratoriosContextType = {
    laboratorios: labType[],
    setLaboratorios: (laboratorios: labType[]) => void,
}

const initialValue = {
    laboratorios: [{
        id: '1',
        name: 'Laboratório 01'
    },{
        id: '2',
        name: 'Laboratório 02'
    }],
    setLaboratorios: () => {},
}

export const LaboratoriosContext = createContext<LaboratoriosContextType>(initialValue);

export const LaboratoriosContextProvider = ({children} : Props) => {
    const [laboratorios, setLaboratorios] = useState(initialValue.laboratorios);

    return (
        <LaboratoriosContext.Provider value={{laboratorios, setLaboratorios}}>
            {children}
        </LaboratoriosContext.Provider>
    )
}