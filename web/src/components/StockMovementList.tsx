import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { getStockMovements, deleteStockMovement } from "../services/stockMovementService";
import "./StockMovementList.css";

export default function StockMovementList() {
  const qc = useQueryClient();

  const { data = [], isLoading } = useQuery({ queryKey: ["stock-movements"], queryFn: getStockMovements });

  const del = useMutation({
    mutationFn: deleteStockMovement,
    onSuccess: () => qc.invalidateQueries({ queryKey: ["stock-movements"] }),
  });

  if (isLoading) return <p>Chargement...</p>;

  return (
    <div className="movement-list-container">
      <h2>Mouvements de stock</h2>
      <ul>
        {data.map((m: any) => (
          <li key={m.id}>
            <div className="movement-details">
              <span><b>{m.type}</b></span>
              <span>{m.quantite} unités</span>
              <span>Date: {m.date}</span>
              <span>Exp: {m.dateexpiration}</span>
              <span>Médicament: {m.nomMedicament} ({m.referenceMedica})</span>
              <span>{m.commentaire}</span>
            </div>
            <div className="movement-actions">
              <button onClick={() => del.mutate(m.id)} className="btn-delete">🗑️</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}
