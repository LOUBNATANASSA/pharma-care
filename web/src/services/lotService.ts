import axios from "axios";

// URL de base pour les appels API
const BASE_URL = "http://localhost:8080/api/lots";

// Définition du type Lot (peut être ajusté selon ton DTO)
export interface Lot {
  id?: number; // optionnel lors de la création
  nom_lot: string;
  dateReception: string; // format ISO: "2025-05-11"
  maxQuantite: number;
  type: string;
}

// Obtenir tous les lots
export async function getLots(): Promise<Lot[]> {
  const res = await axios.get(BASE_URL);
  return res.data;
}

// Obtenir un lot par ID
export async function getLotById(id: number): Promise<Lot> {
  const res = await axios.get(`${BASE_URL}/${id}`);
  return res.data;
}

// Créer un nouveau lot
export async function createLot(lot: Lot): Promise<Lot> {
  const res = await axios.post(BASE_URL, lot);
  return res.data;
}

// Mettre à jour un lot existant
export async function updateLot(id: number, lot: Lot): Promise<Lot> {
  const res = await axios.put(`${BASE_URL}/${id}`, lot);
  return res.data;
}

// Supprimer un lot
export async function deleteLot(id: number): Promise<string> {
  const res = await axios.delete(`${BASE_URL}/${id}`);
  return res.data;
}
