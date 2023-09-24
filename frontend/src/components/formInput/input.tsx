import { ChangeEvent } from "react"
import "./index.css"

interface Props {
    labelName: string,
    icon: "badge" | "lock" | "school" | "mail" | "calendar_today",
    inputName: string,
    handleFunction?: (e : ChangeEvent<HTMLInputElement>) => void,
    aditionalClassName?: string,
    inputType: "text" | "password"
}

export const Input = (props: Props) => {
    return (
        <div className={`input-text ${props.aditionalClassName}`}>
            <label htmlFor={props.inputName}>{props.labelName}:</label>
            <span className="material-symbols-outlined input-icon">{props.icon}</span> 
            <input type={props.inputType} name={props.inputName} id={props.inputName} autoComplete="off" onChange={props.handleFunction}/>
        </div>
    )
}