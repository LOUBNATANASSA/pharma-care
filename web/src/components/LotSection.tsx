// src/components/LotSection.tsx
import { useState } from "react";
import LotForm from "./LotForm";
import LotList from "./LotList";
import "./LotSection.css";

export default function LotSection() {
  const [showForm, setShowForm] = useState(false);

  return (
    <section className="lot-section">
      <div className="lot-toggle-buttons">
        <button
          className={!showForm ? "active" : ""}
          onClick={() => setShowForm(false)}
        >
          📄 Voir les lots
        </button>
        <button
          className={showForm ? "active" : ""}
          onClick={() => setShowForm(true)}
        >
          ➕ Ajouter un lot
        </button>
      </div>

      <div className="lot-content">
        {showForm ? <LotForm /> : <LotList />}
      </div>
    </section>
  );
}
