import { useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { createStockInfo } from "../services/stockInfoService";
import { getLots } from "../services/lotService";
import "./StockInfoForm.css";

export default function StockInfoForm() {
  const [form, setForm] = useState({
    matricule: "",
    quantiteStock: 0,
    date: "",
    lotId: 0,
  });

  const qc = useQueryClient();
  const { data: lots = [] } = useQuery({ queryKey: ["lots"], queryFn: getLots });

  const mut = useMutation({
    mutationFn: createStockInfo,
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["stocks"] });
      setForm({ matricule: "", quantiteStock: 0, date: "", lotId: 0 });
    },
  });

  const handle = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) =>
    setForm({ ...form, [e.target.name]: e.target.type === "number" ? +e.target.value : e.target.value });

  return (
    <div className="stock-form-container">
      <h2>Ajouter un stock</h2>
      <form
        className="stock-form"
        onSubmit={e => {
          e.preventDefault();
          mut.mutate(form);
        }}
      >
        <div className="form-group">
          <label>Matricule</label>
          <input name="matricule" value={form.matricule} onChange={handle} required />
        </div>
        <div className="form-group">
          <label>Quantité</label>
          <input name="quantiteStock" type="number" value={form.quantiteStock} onChange={handle} required />
        </div>
        <div className="form-group">
          <label>Date</label>
          <input name="date" type="date" value={form.date} onChange={handle} required />
        </div>
        <div className="form-group">
          <label>Lot</label>
          <select name="lotId" value={form.lotId} onChange={handle} required>
            <option value="">--Choisir--</option>
            {lots.map((l: any) => (
              <option key={l.id} value={l.id}>{l.nom_lot} (ID {l.id})</option>
            ))}
          </select>
        </div>
        <button className="btn-add">➕ Ajouter</button>
      </form>
    </div>
  );
}
