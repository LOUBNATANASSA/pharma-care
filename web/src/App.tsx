import Home from "./components/Home";
import MedicationSection from "./components/MedicationSection";

import "./App.css";

import { useState } from 'react';
function App() {
  const [menuOpen, setMenuOpen] = useState(false);
  return (
<div className="app">
      <nav className="navbar">
        <div className="logo">PharmaCare</div>

        <div className="burger" onClick={() => setMenuOpen(!menuOpen)}>
          ☰
        </div>

        <ul className={`nav-links ${menuOpen ? "show" : ""}`}>
          <li><a href="#">Accueil</a></li>
          <li><a href="#medications">Médicaments</a></li>
          <li><a href="#">Lots</a></li>
          <li><a href="#">Stocks</a></li>
          <li><a href="#">Dashboard</a></li>
          <li><a href="#">Sign up</a></li>

        </ul>
      </nav>


      <Home />

      <main className="main-grid" id="medications">
  <MedicationSection />
</main>
    </div>
  );
}

export default App;
