import { useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { addMedication } from "../services/medicationService";
import { getLots } from "../services/lotService";
import './MedicationForm.css';

export default function MedicationForm() {
  const [form, setForm] = useState({
    name: "",
    reference: "",
    price: 0,
    dosage: "",
    supplier: "",
    form: "",
    storage: "",
    lot: { lotId: 0 },
    etat_stock: "",
  });

  const queryClient = useQueryClient();

 const { data: lots = [], isLoading: isLotsLoading } = useQuery({
  queryKey: ["lots"],
  queryFn: getLots,
});

  const mutation = useMutation({
    mutationFn: addMedication,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["medications"] });
      setForm({
        name: "",
        reference: "",
        price: 0,
        dosage: "",
        supplier: "",
        form: "",
        storage: "",
        lot: { lotId: 0 },
        etat_stock: "",
      });
    },
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;

    if (name === "lotId") {
      setForm({ ...form, lot: { lotId: parseInt(value) } });
    } else if (name === "price") {
      setForm({ ...form, price: parseFloat(value) });
    } else {
      setForm({ ...form, [name]: value });
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    mutation.mutate(form);
  };

  return (
    <div className="medication-form-container">
      <h2>Ajouter un médicament</h2>
      <form onSubmit={handleSubmit} className="medication-form">
        {/* Champs standards */}
        <div className="form-group">
          <label htmlFor="name">Nom</label>
          <input name="name" value={form.name} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="reference">Référence</label>
          <input name="reference" value={form.reference} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="price">Prix</label>
          <input name="price" value={form.price} onChange={handleChange} type="number" required />
        </div>
        <div className="form-group">
          <label htmlFor="dosage">Dosage</label>
          <input name="dosage" value={form.dosage} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="supplier">Fournisseur</label>
          <input name="supplier" value={form.supplier} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="form">Forme</label>
          <input name="form" value={form.form} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="storage">Stockage</label>
          <input name="storage" value={form.storage} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="lotId">Lot</label>
          {isLotsLoading ? (
            <p>Chargement des lots...</p>
          ) : (
            <select name="lotId" value={form.lot.lotId} onChange={handleChange} required>
              <option value="">-- Choisir un lot --</option>
              {lots.map((lot: any) => (
                <option key={lot.id} value={lot.id}>
                  {lot.nom_lot} (ID: {lot.id})
                </option>
              ))}
            </select>
          )}
        </div>
        <div className="form-group">
          <label htmlFor="etat_stock">État du stock</label>
          <input name="etat_stock" value={form.etat_stock} onChange={handleChange} />
        </div>

        <button type="submit" className="btn-add">➕ Ajouter</button>
      </form>
    </div>
  );
}
