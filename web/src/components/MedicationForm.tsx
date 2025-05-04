import { useState } from "react";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { addMedication } from "../services/medicationService";

export default function MedicationForm() {
  const [medication, setMedication] = useState({
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
      // Réinitialiser le formulaire
      setMedication({
        name: "",
        reference: "",
        price: 0,
        dosage: "",
        supplier: "",
        form: "",
        storage: "",
      });
    },
    onError: (error) => {
      console.error("Erreur d’ajout de médicament :", error);
    },
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setMedication((prev) => ({
      ...prev,
      [name]: name === "price" ? parseFloat(value) || 0 : value,
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    mutation.mutate(medication);
  };

  return (
    <form onSubmit={handleSubmit}>
      {Object.keys(medication).map((key) => (
        <div key={key}>
          <label>{key}</label>
          <input
            type={key === "price" ? "number" : "text"}
            name={key}
            value={medication[key as keyof typeof medication]}
            onChange={handleChange}
            required={key !== "dosage"} // dosage est nullable
          />
        </div>
      ))}
      <button type="submit" disabled={mutation.isPending}>
        {mutation.isPending ? "Ajout..." : "Ajouter"}
      </button>
    </form>
  );
}
