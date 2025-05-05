import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import {
  getMedications,
  deleteMedication,
  updateMedication,
} from "../services/medicationService";
import { useState } from "react";

export default function MedicationList() {
  const queryClient = useQueryClient();
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editForm, setEditForm] = useState<any>({});

  const { data, isLoading, error } = useQuery({
    queryKey: ["medications"],
    queryFn: getMedications,
  });

  const deleteMut = useMutation({
    mutationFn: deleteMedication,
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["medications"] }),
  });

  const updateMut = useMutation({
    mutationFn: ({ id, med }: { id: number; med: any }) =>
      updateMedication(id, med),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["medications"] });
      setEditingId(null);
    },
  });

  if (isLoading) return <p>Chargement des médicaments...</p>;
  if (error) {
    console.error("Erreur lors de la récupération des médicaments :", error);
    return <p style={{ color: "red" }}>Erreur de chargement</p>;
  }

  if (!Array.isArray(data)) return <p>Aucune donnée disponible.</p>;

  return (
    <ul>
      {data.map((med: any) => (
        <li key={med.id}>
          {editingId === med.id ? (
            <form
              onSubmit={(e) => {
                e.preventDefault();
                updateMut.mutate({ id: med.id, med: editForm });
              }}
            >
              <input
                name="name"
                defaultValue={med.name}
                onChange={(e) =>
                  setEditForm({ ...editForm, name: e.target.value })
                }
              />
              <input
                name="reference"
                defaultValue={med.reference}
                onChange={(e) =>
                  setEditForm({ ...editForm, reference: e.target.value })
                }
              />
              <input
                name="price"
                type="number"
                defaultValue={med.price}
                onChange={(e) =>
                  setEditForm({ ...editForm, price: parseFloat(e.target.value) })
                }
              />
              <button type="submit">💾</button>
              <button type="button" onClick={() => setEditingId(null)}>
                ❌
              </button>
            </form>
          ) : (
            <>
              {med.name} — {med.reference} — {med.dosage} — {med.price} €
              <button onClick={() => {
                setEditingId(med.id);
                setEditForm(med);
              }}>✏️</button>
              <button onClick={() => deleteMut.mutate(med.id)}>🗑️</button>
            </>
          )}
        </li>
      ))}
    </ul>
  );
}
