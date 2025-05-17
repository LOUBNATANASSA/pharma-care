import Home from "./components/Home";
import MedicationSection from "./components/MedicationSection";
import LotSection from "./components/LotSection";
import StockMovementSection from "./components/StockMovementSection"; 
import StockInfoSection from "./components/StockInfoSection"; 

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
          <li><a href="#lots">Lots</a></li>
          <li><a href="#stocks">Stocks</a></li>
          <li><a href="#stocke">Stocks Movement</a></li>
          <li><a href="#">Sign up</a></li>

        </ul>
      </nav>


      <Home />

      <main className="main-grid" id="medications">
  <MedicationSection />
</main>
      <main className="main-grid" id="lots">
      <LotSection />
    </main>
      <main className="main-grid" id="stocks">
        <StockInfoSection />
      </main>
       <main className="main-grid" id="stocke">
        <StockMovementSection />
      </main>
    </div>
  );
}

export default App;
