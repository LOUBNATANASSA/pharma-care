import axios from "axios";

const BASE_URL = "http://localhost:8080/api/stockinfo";

export interface StockInfo {
  id?: number;
  matricule: string;
  quantiteStock: number;
  date: string;        // "2025-05-29"
  lotId: number;
}

export const getStockInfos = () =>
  axios.get(BASE_URL).then(r => r.data as StockInfo[]);

export const createStockInfo = (s: StockInfo) =>
  axios.post(BASE_URL, s).then(r => r.data);

export const updateStockInfo = (id: number, s: StockInfo) =>
  axios.put(`${BASE_URL}/${id}`, s).then(r => r.data);

export const deleteStockInfo = (id: number) =>
  axios.delete(`${BASE_URL}/${id}`).then(r => r.data);
