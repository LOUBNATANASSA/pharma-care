// src/components/MedicationSection.tsx
import { useState } from "react";
import MedicationForm from "./MedicationForm";
import MedicationList from "./MedicationList";
import "./MedicationSection.css";

export default function MedicationSection() {
  const [showForm, setShowForm] = useState(false);

  return (
    <section className="medication-section">
      <div className="medication-toggle-buttons">
        <button
          className={!showForm ? "active" : ""}
          onClick={() => setShowForm(false)}
        >
          📄 Voir la liste
        </button>
        <button
          className={showForm ? "active" : ""}
          onClick={() => setShowForm(true)}
        >
          ➕ Ajouter un médicament
        </button>
      </div>

      <div className="medication-content">
        {showForm ? <MedicationForm /> : <MedicationList />}
      </div>
    </section>
  );
}
