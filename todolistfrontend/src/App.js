import Loginpage from "./Loginpage.jsx";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from "./Home.jsx";
function App() {
  return (
    <Router>
          <Routes>
          <Route path="/login" element={<Loginpage />} />
          <Route path="/home" element={<Home/>} />
        </Routes>
      </Router>
    
  );
}

export default App;
