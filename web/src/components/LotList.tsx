// src/components/LotList.tsx
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { getLots, deleteLot, updateLot } from "../services/lotService";
import { useState } from "react";
import './LotList.css';

export default function LotList() {
  const queryClient = useQueryClient();
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editForm, setEditForm] = useState<any>({});

  const { data, isLoading, error } = useQuery({
    queryKey: ["lots"],
    queryFn: getLots,
  });

  const deleteMut = useMutation({
    mutationFn: deleteLot,
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["lots"] }),
  });

  const updateMut = useMutation({
    mutationFn: ({ id, lot }: { id: number; lot: any }) =>
      updateLot(id, lot),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["lots"] });
      setEditingId(null);
    },
  });

  if (isLoading) return <p className="status-message loading">Chargement des lots...</p>;
  if (error) {
    console.error("Erreur lors de la récupération des lots :", error);
    return <p className="status-message error">Erreur de chargement</p>;
  }

  if (!Array.isArray(data)) return <p className="status-message no-data">Aucune donnée disponible.</p>;

  return (
    <div className="lot-list-container">
      <h2>Liste des lots</h2>
      <ul>
        {data.map((lot: any) => (
          <li key={lot.id} className={editingId === lot.id ? "editing" : ""}>
            {editingId === lot.id ? (
              <form
                className="edit-form"
                onSubmit={(e) => {
                  e.preventDefault();
                  updateMut.mutate({ id: lot.id, lot: editForm });
                }}
              >
                <input
                  name="Nom_lot"
                  defaultValue={lot.Nom_lot}
                  onChange={(e) =>
                    setEditForm({ ...editForm, Nom_lot: e.target.value })
                  }
                />
                <input
                  name="type"
                  defaultValue={lot.type}
                  onChange={(e) =>
                    setEditForm({ ...editForm, type: e.target.value })
                  }
                />
                <input
                  name="maxQuantite"
                  type="number"
                  defaultValue={lot.maxQuantite}
                  onChange={(e) =>
                    setEditForm({ ...editForm, maxQuantite: parseInt(e.target.value) })
                  }
                />
                <input
                  name="dateReception"
                  type="date"
                  defaultValue={lot.dateReception}
                  onChange={(e) =>
                    setEditForm({ ...editForm, dateReception: e.target.value })
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
                <div className="lot-details">
                  <span className="lot-name">{lot.Nom_lot}</span>
                  <span>{lot.type}</span>
                  <span>{lot.maxQuantite} unités</span>
                  <span>{lot.dateReception}</span>
                </div>
                <div className="lot-actions">
                  <button
                    onClick={() => {
                      setEditingId(lot.id);
                      setEditForm(lot);
                    }}
                    className="btn-edit"
                  >
                    ✏️
                  </button>
                  <button
                    onClick={() => deleteMut.mutate(lot.id)}
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
