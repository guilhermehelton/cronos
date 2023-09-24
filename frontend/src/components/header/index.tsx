import './index.css';

interface Props {
    tittle?: string,
    icon?: 'home' | 'groups',
}

export const Header = (props : Props) => {
    return (
        <div className='retangulo-fundo'>
            <span className="material-symbols-outlined">{props.icon}</span>
            <h1>{props.tittle}</h1>
        </div>
    )
}