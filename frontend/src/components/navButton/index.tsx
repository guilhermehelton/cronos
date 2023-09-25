/* eslint-disable @typescript-eslint/no-unused-vars */
import { MouseEvent, useState } from "react"
import "./index.css"
import { labType } from "../../types/labListType";
import { useNavigate } from "react-router-dom";

interface Props {
    label: string,
    icon?: 'home' | 'groups' | 'person_add' | 'logout',
    handleFunction?: (e : MouseEvent<HTMLAnchorElement>) => void,
    itemList?: labType[],
    isDropdown?: boolean
}

export const NavButton = (props : Props) => {
    const [menuDropdownState, setMenuDropdownState] = useState('hide');
    const navigate = useNavigate();

    const handleChangeDropownMenu = () => {
        if(menuDropdownState == 'hide'){
            setMenuDropdownState('');
        }else {
            setMenuDropdownState('hide');
        }
    }

    const handleChangeLab = (idLab : string) => {
        navigate(`/laboratorio/${idLab}`)
    }

    const handleChangeToAddLab = () => {
        navigate('/laboratorio/add')
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
                        <li onClick={e => handleChangeLab(item.id)}>
                            <span className="material-symbols-outlined">computer</span>
                            <span className="label">{item.name}</span>
                        </li>
                    ))
                }
                <li  onClick={e => handleChangeToAddLab()}>
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