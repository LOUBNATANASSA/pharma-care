import { useState } from "react";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { addMedication } from "../services/medicationService";

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
    <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
      {Object.keys(form).map((key) => (
        <input
          key={key}
          name={key}
          value={form[key as keyof typeof form]}
          onChange={handleChange}
          placeholder={key}
          style={{ margin: "5px", padding: "5px" }}
        />
      ))}
      <button type="submit" style={{ padding: "5px 10px" }}>➕ Ajouter</button>
    </form>
  );
}
