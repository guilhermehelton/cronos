import { ReactNode, createContext, useEffect, useState } from "react";
import { Usuario } from "../types/usuarioType";
import { LoginData } from "../types/loginType";

type Props = {
    children: ReactNode
}

type AuthContextType = {
    authToken: string,
    setAuthToken: (token: string) => void,
    usuario: Usuario,
    setUsuario: (usuario: Usuario) => void,
    signed: boolean,
    setSigned: (state: boolean) => void,
}

const initialValue = {
    authToken: '',
    setAuthToken: () => {},
    usuario: {} as Usuario,
    setUsuario: () => {},
    loginData: {} as LoginData,
    setLoginData: () => {},
    signed: false,
    setSigned: () => {}
}

export const AuthContext = createContext<AuthContextType>(initialValue);

export const AuthContextProvider = ({children} : Props) => {
    const [authToken, setAuthToken] = useState(initialValue.authToken);
    const [usuario, setUsuario] = useState(initialValue.usuario);
    const [signed, setSigned] = useState(initialValue.signed);

    useEffect(() => {
        const loadStoreAuth = () => {
          const token = sessionStorage.getItem("@Auth:token");
          const sessionUser: Usuario = JSON.parse(sessionStorage.getItem("@Auth:user") ?? "{}");
          if (token && sessionUser.matricula) {
            setAuthToken(token);
            setUsuario(sessionUser);
            setSigned(true);
          }
        };
        loadStoreAuth();
      }, []);

    return (
        <AuthContext.Provider value={{authToken, setAuthToken, usuario, setUsuario, signed, setSigned}}>
            {children}
        </AuthContext.Provider>
    )
}