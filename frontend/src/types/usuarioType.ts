import { Authorities } from "./authoritiesType"
import { RoleEnum } from "./roleEnum"

export type Usuario = {
    id: string,
    nome: string,
    email: string,
    dataNascimento: string,
    senha: string,
    curso: string,
    matricula: string,
    role: RoleEnum,
    cargaHorariaTotal: 0,
    enabled: true,
    authorities: Authorities[],
    username: string,
    password: string,
    accountNonExpired: true,
    accountNonLocked: true,
    credentialsNonExpired: true
}