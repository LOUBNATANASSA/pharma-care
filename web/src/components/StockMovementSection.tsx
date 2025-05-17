import { useState } from "react";
import StockMovementForm from "./StockMovementForm";
import StockMovementList from "./StockMovementList";
import "./StockMovementSection.css";

export default function StockMovementSection() {
  const [showForm, setShowForm] = useState(false);

  return (
    <section className="movement-section">
      <div className="movement-toggle-buttons">
        <button className={!showForm ? "active" : ""} onClick={() => setShowForm(false)}>📄 Voir les mouvements</button>
        <button className={showForm ? "active" : ""} onClick={() => setShowForm(true)}>➕ Ajouter un mouvement</button>
      </div>
      {showForm ? <StockMovementForm /> : <StockMovementList />}
    </section>
  );
}
