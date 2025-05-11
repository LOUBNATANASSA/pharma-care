import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { getMedications, deleteMedication, updateMedication } from "../services/medicationService";
import { useState } from "react";
import './MedicationList.css';

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
    mutationFn: ({ id, med }: { id: number; med: any }) => updateMedication(id, med),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["medications"] });
      setEditingId(null);
    },
  });

  if (isLoading) return <p className="status-message loading">Chargement des médicaments...</p>;
  if (error) {
    console.error("Erreur lors de la récupération des médicaments :", error);
    return <p className="status-message error">Erreur de chargement</p>;
  }

  if (!Array.isArray(data)) return <p className="status-message no-data">Aucune donnée disponible.</p>;

  return (
    <div className="medication-list-container">
      <h2>Liste des médicaments</h2>
      <ul>
        {data.map((med: any) => (
          <li key={med.id} className={editingId === med.id ? "editing" : ""}>
            {editingId === med.id ? (
              <form
                className="edit-form"
                onSubmit={(e) => {
                  e.preventDefault();
                  updateMut.mutate({ id: med.id, med: editForm });
                }}
              >
                <input
                  name="name"
                  defaultValue={med.name}
                  onChange={(e) => setEditForm({ ...editForm, name: e.target.value })}
                />
                <input
                  name="reference"
                  defaultValue={med.reference}
                  onChange={(e) => setEditForm({ ...editForm, reference: e.target.value })}
                />
                <input
                  name="price"
                  type="number"
                  defaultValue={med.price}
                  onChange={(e) => setEditForm({ ...editForm, price: parseFloat(e.target.value) })}
                />
                <input
                  name="dosage"
                  defaultValue={med.dosage}
                  onChange={(e) => setEditForm({ ...editForm, dosage: e.target.value })}
                />
                <input
                  name="supplier"
                  defaultValue={med.supplier}
                  onChange={(e) => setEditForm({ ...editForm, supplier: e.target.value })}
                />
                <input
                  name="form"
                  defaultValue={med.form}
                  onChange={(e) => setEditForm({ ...editForm, form: e.target.value })}
                />
                <input
                  name="storage"
                  defaultValue={med.storage}
                  onChange={(e) => setEditForm({ ...editForm, storage: e.target.value })}
                />
                <input
                  name="etat_stock"
                  defaultValue={med.etat_stock}
                  onChange={(e) => setEditForm({ ...editForm, etat_stock: e.target.value })}
                />
                <input
                  name="lotId"
                  type="number"
                  defaultValue={med.lot?.id || 0}
                  onChange={(e) =>
                    setEditForm({ ...editForm, lot: { lotId: parseInt(e.target.value) } })
                  }
                />

                <button type="submit" className="btn-save">💾</button>
                <button
                  type="button"
                  onClick={() => setEditingId(null)}
                  className="btn-cancel"
                >
                  ❌
                </button>
              </form>
            ) : (
              <>
                <div className="medication-details">
                  <span className="med-name">{med.name}</span>
                  <span>{med.reference}</span>
                  <span>{med.price} DH</span>
                  <span>{med.dosage}</span>
                  <span>{med.supplier}</span>
                  <span>{med.form}</span>
                  <span>{med.storage}</span>
                  <span>{med.etat_stock}</span>
                  <span>Lot: {med.lot?.nom_lot} (ID: {med.lot?.id})</span>
                </div>
                <div className="medication-actions">
                  <button
                    onClick={() => {
                      setEditingId(med.id);
                      setEditForm({
                        ...med,
                        lot: { lotId: med.lot?.id || 0 },
                      });
                    }}
                    className="btn-edit"
                  >
                    ✏️
                  </button>
                  <button
                    onClick={() => deleteMut.mutate(med.id)}
                    className="btn-delete"
                  >
                    🗑️
                  </button>
                </div>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
