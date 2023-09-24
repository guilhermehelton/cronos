import { useNavigate } from "react-router-dom";
import { Header } from "../../components/header"
import './index.css'
import { Input } from "../../components/formInput/input";
import { ChangeEvent } from "react";

export const Cadastro = () => {
    const navigate = useNavigate();

    const handleCancelar = () => {
        navigate("/");
    }

    const handleConfirmar = () => {
        navigate("/");
    }

    const handleChange = (e : ChangeEvent<HTMLInputElement>) => {
        console.log(e.target.value);
    }


    return(
       <div className="signup-page">
            <Header tittle="CRONOS"></Header>
            <div className="signup-form">
                <h1>Cadastre-se</h1>
                <div className="form-2-col">
                    <Input inputType="text" icon="school" inputName="matricula" labelName="Matricula" handleFunction={handleChange} aditionalClassName="margin-right"/>
                    <Input inputType="text" icon="badge" inputName="nome" labelName="Nome"/>
                </div>
                <div className="form-2-col">
                    <Input inputType="text" icon="mail" inputName="email" labelName="Email" aditionalClassName="margin-right"/>
                    <Input inputType="text" icon="calendar_today" inputName="dtNascimento" labelName="Data de nascimento"/>
                </div>
                <div className="form-2-col">
                    <Input inputType="text" icon="school" inputName="curso" labelName="Curso" aditionalClassName="margin-right"/>
                    <Input inputType="password" icon="lock" inputName="senha" labelName="Senha"/>
                </div>
                <div className="form-buttons">
                    <button type="button" className="secondary-btn" onClick={handleCancelar}>Cancelar</button>
                    <button type="button" className="primary-btn" onClick={handleConfirmar}>Confirmar</button>
                </div>
            </div>
       </div>
    )
}