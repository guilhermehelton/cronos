import { useNavigate } from "react-router-dom";
import { Header } from "../../components/header"
import "./index.css"
import { Input } from "../../components/formInput/input";

export const Login = () => {
    const navigate = useNavigate();

    const handleNavigateToCadastro = () => {
        navigate("/cadastro");
    }

    const handleLogin = () => {
        navigate("/home");
    }


    return (
        <div className="login-page">
            <Header tittle="CRONOS"></Header>
            <div className="login-form">
                <h1>Bem vindo!</h1>
                <Input inputType="text" icon="badge" inputName="matricula" labelName="Login"/>
                <Input inputType="password" icon="lock" inputName="senha" labelName="Senha"/>
                <div className="form-buttons">
                    <button type="button" className="secondary-btn" onClick={handleNavigateToCadastro}>Cadastrar-se</button>
                    <button type="button" className="primary-btn" onClick={handleLogin}>Entrar</button>
                </div>
            </div>
        </div>
    )
}