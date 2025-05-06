import { useState } from "react";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { addMedication } from "../services/medicationService";
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
  });

  const queryClient = useQueryClient();

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
    <div className="medication-form-container">
      <h2>Ajouter un médicament</h2>
      <form onSubmit={handleSubmit} className="medication-form">
        {Object.keys(form).map((key) => (
          <div className="form-group" key={key}>
             <label htmlFor={key}>{key.charAt(0).toUpperCase() + key.slice(1)}</label>

            <input
              name={key}
              value={form[key as keyof typeof form]} 
              onChange={handleChange}
              placeholder={key}
              type={key === "price" ? "number" : "text"}
            />
          </div>
        ))}
        <button type="submit" className="btn-add">➕ Ajouter</button>
      </form>
    </div>
  );
}
