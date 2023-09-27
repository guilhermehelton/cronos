import './index.css'

export const TopBar = () => {
    return(
        <div className="topBar">
            <div className='topBarButtons'>
                <span>Tarefas</span>
                <span className='midButton'>Reuniões</span>
                <span>Equipe</span>
                <hr />
            </div>
        </div>
    )
}