/* eslint-disable @typescript-eslint/no-unused-vars */
import { MouseEvent, useContext, useState } from "react"
import "./index.css"
import { labType } from "../../types/labListType";
import { PaginaContext } from "../../contexts/PaginaContext";
import { PaginasEnum } from "../../types/paginasEnum";

interface Props {
    label: string,
    icon?: 'home' | 'groups' | 'person_add' | 'logout',
    handleFunction?: (e : MouseEvent<HTMLAnchorElement>) => void,
    itemList?: labType[],
    isDropdown?: boolean
}

export const NavButton = (props : Props) => {
    const [menuDropdownState, setMenuDropdownState] = useState('hide');
    const {handleTrocaPagina} = useContext(PaginaContext);

    const handleChangeDropownMenu = () => {
        if(menuDropdownState == 'hide'){
            setMenuDropdownState('');
        }else {
            setMenuDropdownState('hide');
        }
    }

    const handleChangeLab = (nomeLab : string) => {
        handleTrocaPagina(PaginasEnum.LAB, nomeLab, 'groups');
    }

    return (
        props.isDropdown ?
        <div className="navButton dropdown">
            <a onClick={handleChangeDropownMenu}>
                <span className="material-symbols-outlined">{props.icon}</span>
                <span className="label">{props.label}</span>
                <span className="material-symbols-outlined end-line">expand_more</span>
            </a>
            <ul className={`labList ${menuDropdownState}`}>
                {
                    props.itemList?.map(item => (
                        <li onClick={e => handleChangeLab(item.name)}>
                            <span className="material-symbols-outlined">computer</span>
                            <span className="label">{item.name}</span>
                        </li>
                    ))
                }
                <li  onClick={e => handleChangeLab('Adicionar Laboratório')}>
                    <span className="material-symbols-outlined">add</span>
                    <span className="label">Adicionar</span>
                </li>
            </ul>
        </div>
        :
        <div className="navButton">
            <a onClick={props.handleFunction}>
                <span className="material-symbols-outlined">{props.icon}</span>
                <span className="label">{props.label}</span>
            </a>
        </div>
    )
}