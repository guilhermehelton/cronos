import './App.css'
import { LaboratoriosContextProvider } from './contexts/LaboratorioContext'
import { AppRoutes } from './routes/routes'

function App() {

  return (
    <LaboratoriosContextProvider>
      <AppRoutes/>
    </LaboratoriosContextProvider>
  )
}

export default App
