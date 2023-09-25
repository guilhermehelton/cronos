import './App.css'
import { LaboratoriosContextProvider } from './contexts/PaginaContext'
import { AppRoutes } from './routes/routes'

function App() {

  return (
    <LaboratoriosContextProvider>
      <AppRoutes/>
    </LaboratoriosContextProvider>
  )
}

export default App
