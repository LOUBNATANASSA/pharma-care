import { useState } from "react";
import StockInfoForm from "./StockInfoForm";
import StockInfoList from "./StockInfoList";
import "./StockInfoSection.css";

export default function StockInfoSection() {
  const [showForm, setShowForm] = useState(false);

  return (
    <section className="stock-section">
      <div className="stock-toggle-buttons">
        <button className={!showForm ? "active" : ""} onClick={() => setShowForm(false)}>📄 Voir la liste</button>
        <button className={showForm ? "active" : ""} onClick={() => setShowForm(true)}>➕ Ajouter un stock</button>
      </div>

      {showForm ? <StockInfoForm /> : <StockInfoList />}
    </section>
  );
}
