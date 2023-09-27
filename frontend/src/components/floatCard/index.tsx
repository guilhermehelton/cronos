import "./index.css"

type Props = {
    titulo: string,
    icon: 'description' | 'groups',
    info: string
    isSecond?: boolean
}

export const FloatCard = (props: Props) => {
    return (
        <div className={`floatCard ${props.isSecond ? 'second' : ''}`}>
            <div className="tituloCard">
                <span>{props.titulo}</span>
                <span className="material-symbols-outlined cardIcon">{props.icon}</span>
            </div>
            <span className="cardNumber">{props.info}</span>
        </div>
    )
}