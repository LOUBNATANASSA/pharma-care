import axios from "axios";

const BASE_URL = "http://localhost:8080/api/stock-movements";

export interface StockMovement {
  id?: number;
  type: string;
  date: string;
  quantite: number;
  dateExpiration: string;
  commentaire: string;
  medicamentId: number;
}

export const getStockMovements = () => axios.get(BASE_URL).then(res => res.data);
export const createStockMovement = (m: StockMovement) => axios.post(BASE_URL, m).then(res => res.data);
export const updateStockMovement = (id: number, m: StockMovement) => axios.put(`${BASE_URL}/${id}`, m).then(res => res.data);
export const deleteStockMovement = (id: number) => axios.delete(`${BASE_URL}/${id}`).then(res => res.data);
