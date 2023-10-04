import { useNavigate } from "react-router-dom";
import { Header } from "../../components/header"
import './index.css'
import { Input } from "../../components/formInput/input";
import { useState } from "react";
import { RegisterUserType } from "../../types/registerUserType";
import { RoleEnum } from "../../types/roleEnum";

export const Cadastro = () => {
    const [nome, setNome] = useState('');
    const [matricula, setMatricula] = useState('');
    const [email, setEmail] = useState('');
    const [dataNascimento, setDataNascimento] = useState('');
    const [curso, setCurso] = useState('');
    const [senha, setSenha] = useState('');
    const navigate = useNavigate();

    const dataValida = (data: string) => {
        const regex = /^(\d{2})\/(\d{2})\/(\d{4})$/;
  
        const match = data.match(regex);
  
        if (match) {
            return match
        } else {
            alert("Data no formato invalido. Tente dd/MM/yyyy")
        }
    }

    const formataData = (match: RegExpMatchArray) => {
        const dia = parseInt(match[1], 10);
        const mes = parseInt(match[2], 10) - 1;
        const ano = parseInt(match[3], 10);
            
        const data = new Date(ano, mes, dia);
            
        return data.toISOString();
    }

    const handleCancelar = () => {
        navigate("/");
    }

    const handleConfirmar = () => {
        const isDataNascimentoValid = dataValida(dataNascimento);
        if(isDataNascimentoValid){
            const usuario : RegisterUserType = {
                curso,
                dataNascimento: formataData(isDataNascimentoValid),
                email,
                matricula,
                nome,
                senha,
                role: RoleEnum.ALUNO,
            }

            fetch('http://localhost:8444/auth/register', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(usuario),
                }).then((response) => {
                    if(!response.ok){
                        return Promise.reject(response);
                    }
                    return response.json();
                }).then(() => {
                    navigate("/");
                })
        }
    }

    return(
       <div className="signup-page">
            <Header tittle="CRONOS"></Header>
            <div className="signup-form">
                <h1>Cadastre-se</h1>
                <div className="form-2-col">
                    <Input inputType="text" icon="school" inputName="matricula" labelName="Matricula" handleFunction={e => setMatricula(e.target.value)} aditionalClassName="margin-right"/>
                    <Input inputType="text" icon="badge" inputName="nome" labelName="Nome" handleFunction={e => setNome(e.target.value)}/>
                </div>
                <div className="form-2-col">
                    <Input inputType="text" icon="mail" inputName="email" labelName="Email" handleFunction={e => setEmail(e.target.value)} aditionalClassName="margin-right"/>
                    <Input inputType="text" icon="calendar_today" inputName="dtNascimento" labelName="Data de nascimento" handleFunction={e => setDataNascimento(e.target.value)}/>
                </div>
                <div className="form-2-col">
                    <Input inputType="text" icon="school" inputName="curso" labelName="Curso" handleFunction={e => setCurso(e.target.value)} aditionalClassName="margin-right"/>
                    <Input inputType="password" icon="lock" inputName="senha" labelName="Senha" handleFunction={e => setSenha(e.target.value)}/>
                </div>
                <div className="form-buttons">
                    <button type="button" className="secondary-btn" onClick={handleCancelar}>Cancelar</button>
                    <button type="button" className="primary-btn" onClick={handleConfirmar}>Confirmar</button>
                </div>
            </div>
       </div>
    )
}