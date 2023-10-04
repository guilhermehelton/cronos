import './App.css'
import { AuthContextProvider } from './contexts/AuthContext'
import { LaboratoriosContextProvider } from './contexts/LaboratoriosContext'
import { AppRoutes } from './routes/routes'

function App() {

  return (
    <AuthContextProvider>
      <LaboratoriosContextProvider>
        <AppRoutes/>
      </LaboratoriosContextProvider>
    </AuthContextProvider>
  )
}

export default App
