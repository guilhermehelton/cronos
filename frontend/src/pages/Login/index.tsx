import { Navigate, useNavigate } from "react-router-dom";
import { Header } from "../../components/header"
import "./index.css"
import { Input } from "../../components/formInput/input";
import { useContext, useState } from "react";
import { AuthContext } from "../../contexts/AuthContext";

export const Login = () => {
    const navigate = useNavigate();
    const {setSigned, signed, setUsuario, setAuthToken} = useContext(AuthContext);
    const [matricula, setMatricula] = useState('');
    const [senha, setSenha] = useState('');

    const handleNavigateToCadastro = () => {
        navigate("/cadastro");
    }

    const handleLogin = () => {
        if(matricula && senha) {
            fetch('http://localhost:8444/auth/login', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                matricula: matricula,
                senha: senha,
            }),
            }).then((response) => response.json()).then((data) => {
                sessionStorage.setItem("@Auth:token", data.token);
                sessionStorage.setItem("@Auth:user", JSON.stringify(data.usuario));
                setAuthToken(data.token);
                setUsuario(data.usuario);
                setSigned(true);
                return <Navigate to="/home"/>
            })
        } else {
            alert("Preenchar a matrícula e a senha");
        }
    }


    return (
        signed ?
        <Navigate to="/home"/>
        :
        <div className="login-page">
            <Header tittle="CRONOS"></Header>
            <div className="login-form">
                <h1>Bem vindo!</h1>
                <Input inputType="text" icon="badge" inputName="matricula" labelName="Login" handleFunction={e => setMatricula(e.target.value)}/>
                <Input inputType="password" icon="lock" inputName="senha" labelName="Senha" handleFunction={e => setSenha(e.target.value)}/>
                <div className="form-buttons">
                    <button type="button" className="secondary-btn" onClick={handleNavigateToCadastro}>Cadastrar-se</button>
                    <button type="button" className="primary-btn" onClick={handleLogin}>Entrar</button>
                </div>
            </div>
        </div>
    )
}