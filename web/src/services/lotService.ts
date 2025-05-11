import axios from "axios";

const BASE_URL = "http://localhost:8080/api/lots";

// Obtenir tous les lots
export async function getLots() {
  const res = await axios.get(BASE_URL);
  return res.data;
}

// Obtenir un lot par ID
export async function getLotById(id: number) {
  const res = await axios.get(`${BASE_URL}/${id}`);
  return res.data;
}

// Créer un nouveau lot
export async function createLot(lot: any) {
  const res = await axios.post(BASE_URL, lot);
  return res.data;
}

// Mettre à jour un lot existant
export async function updateLot(id: number, lot: any) {
  const res = await axios.put(`${BASE_URL}/${id}`, lot);
  return res.data;
}

// Supprimer un lot
export async function deleteLot(id: number) {
  const res = await axios.delete(`${BASE_URL}/${id}`);
  return res.data;
}
