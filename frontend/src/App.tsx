import './App.css'
import { PaginaContextProvider } from './contexts/PaginaContext'
import { AppRoutes } from './routes/routes'

function App() {

  return (
    <PaginaContextProvider>
      <AppRoutes/>
    </PaginaContextProvider>
  )
}

export default App
