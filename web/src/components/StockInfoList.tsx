import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { getStockInfos, deleteStockInfo, updateStockInfo } from "../services/stockInfoService";
import "./StockInfoList.css";

export default function StockInfoList() {
  const qc = useQueryClient();
  const { data = [], isLoading, error } = useQuery({ queryKey: ["stocks"], queryFn: getStockInfos });
  const [editing, setEditing] = useState<number | null>(null);
  const [editForm, setEditForm] = useState<any>({});

  const del = useMutation({ mutationFn: deleteStockInfo, onSuccess: () => qc.invalidateQueries({ queryKey: ["stocks"] }) });
  const upd = useMutation({
    mutationFn: ({ id, s }: any) => updateStockInfo(id, s),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["stocks"] });
      setEditing(null);
    },
  });

  if (isLoading) return <p>Chargement…</p>;
  if (error)      return <p>Erreur de chargement</p>;

  return (
    <div className="stock-list-container">
      <h2>Liste des stocks</h2>
      <ul>
        {data.map((s: any) => (
          <li key={s.id} className={editing === s.id ? "editing" : ""}>
            {editing === s.id ? (
              <form
                className="edit-form"
                onSubmit={e => { e.preventDefault(); upd.mutate({ id: s.id, s: editForm }); }}
              >
                <input defaultValue={s.matricule} name="matricule"
                       onChange={e => setEditForm({ ...editForm, matricule: e.target.value })} />
                <input defaultValue={s.quantiteStock} type="number" name="quantiteStock"
                       onChange={e => setEditForm({ ...editForm, quantiteStock: +e.target.value })} />
                <input defaultValue={s.date} type="date" name="date"
                       onChange={e => setEditForm({ ...editForm, date: e.target.value })} />
                <input defaultValue={s.lot.id} type="number" name="lotId"
                       onChange={e => setEditForm({ ...editForm, lotId: +e.target.value })} />
                <button className="btn-save">💾</button>
                <button type="button" onClick={() => setEditing(null)} className="btn-cancel">❌</button>
              </form>
            ) : (
              <>
                <div className="stock-details">
                  <span><b>{s.matricule}</b></span>
                  <span>{s.quantiteStock} u.</span>
                  <span>{s.date}</span>
                  <span>Lot { s.lot.nom_lot } (ID { s.lot.id })</span>
                </div>
                <div className="stock-actions">
                  <button onClick={() => { setEditing(s.id); setEditForm(s); }} className="btn-edit">✏️</button>
                  <button onClick={() => del.mutate(s.id)} className="btn-delete">🗑️</button>
                </div>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
