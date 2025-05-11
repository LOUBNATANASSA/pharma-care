import { useState } from "react";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { createLot } from "../services/lotService";
import './LotForm.css';

export default function LotForm() {
  const [form, setForm] = useState({
    nom_lot: "",
    dateReception: "",
    maxQuantite: 0,
    type: ""
  });

  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: createLot,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["lots"] });
      setForm({
        nom_lot: "",
        dateReception: "",
        maxQuantite: 0,
        type: ""
      });
    },
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    mutation.mutate(form);
  };

  return (
    <div className="lot-form-container">
      <h2>Ajouter un lot</h2>
      <form onSubmit={handleSubmit} className="lot-form">
        <div className="form-group">
          <label htmlFor="nom_lot">Nom du lot</label>
          <input
            name="nom_lot"
            value={form.nom_lot}
            onChange={handleChange}
            placeholder="Nom du lot"
            type="text"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="dateReception">Date de réception</label>
          <input
            name="dateReception"
            value={form.dateReception}
            onChange={handleChange}
            placeholder="Date de réception"
            type="date"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="maxQuantite">Quantité maximale</label>
          <input
            name="maxQuantite"
            value={form.maxQuantite}
            onChange={handleChange}
            placeholder="Quantité maximale"
            type="number"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="type">Type</label>
          <input
            name="type"
            value={form.type}
            onChange={handleChange}
            placeholder="Type"
            type="text"
            required
          />
        </div>

        <button type="submit" className="btn-add">➕ Ajouter</button>
      </form>
    </div>
  );
}
