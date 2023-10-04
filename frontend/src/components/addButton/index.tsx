import './index.css'

type Props = {
    handleFunction: () => void,
}

export const AddButton = (props: Props) => {
    return(
        <div className="addButton" onClick={props.handleFunction}>
            <span className="material-symbols-outlined">add</span>
            <span>Adicionar</span>
        </div>
    )
}