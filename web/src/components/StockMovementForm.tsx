import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import { createStockMovement } from "../services/stockMovementService";
import { getMedications } from "../services/medicationService";
import "./StockMovementForm.css";

export default function StockMovementForm() {
  const [form, setForm] = useState({
    type: "entrée",
    date: "",
    quantite: 0,
    dateExpiration: "",
    commentaire: "",
    medicamentId: 0,
  });

  const qc = useQueryClient();
  const { data: meds = [] } = useQuery({ queryKey: ["medications"], queryFn: getMedications });

  const mut = useMutation({
    mutationFn: createStockMovement,
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["stock-movements"] });
      setForm({ type: "entrée", date: "", quantite: 0, dateExpiration: "", commentaire: "", medicamentId: 0 });
    }
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) =>
    setForm({ ...form, [e.target.name]: e.target.type === "number" ? +e.target.value : e.target.value });

  return (
    <div className="movement-form-container">
      <h2>Ajouter un mouvement de stock</h2>
      <form className="movement-form" onSubmit={(e) => {
        e.preventDefault();
        mut.mutate(form);
      }}>
        <select name="type" value={form.type} onChange={handleChange}>
          <option value="entrée">Entrée</option>
          <option value="sortie">Sortie</option>
        </select>

        <input type="date" name="date" value={form.date} onChange={handleChange} required />
        <input type="number" name="quantite" value={form.quantite} onChange={handleChange} placeholder="Quantité" required />
        <input type="date" name="dateExpiration" value={form.dateExpiration} onChange={handleChange} required />
        <textarea name="commentaire" value={form.commentaire} onChange={handleChange} placeholder="Commentaire" required />

        <select name="medicamentId" value={form.medicamentId} onChange={handleChange} required>
          <option value="">-- Choisir un médicament --</option>
          {meds.map((m: any) => (
            <option key={m.id} value={m.id}>{m.name} ({m.reference})</option>
          ))}
        </select>

        <button type="submit" className="btn-add">➕ Ajouter</button>
      </form>
    </div>
  );
}
