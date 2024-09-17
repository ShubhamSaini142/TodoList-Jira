import Loginpage from "./Loginpage.jsx";
import RegisterPage from "./RegisterPage.jsx"
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from "./Home.jsx";
function App() {
  const isAuthenticated = localStorage.getItem('authtoken')
  return (
    <Router>
          <Routes>
          <Route path="/" element={<Loginpage />} />
          <Route path="/login" element={<Loginpage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/home" element={ <Home/>}/>
        </Routes>
      </Router>
    
  );
}

export default App;
