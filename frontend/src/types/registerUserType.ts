import { RoleEnum } from "./roleEnum"

export type RegisterUserType = {
    nome: string,
    email: string,
    dataNascimento: string,
    senha: string,
    curso: string,
    matricula: string,
    role: RoleEnum
}