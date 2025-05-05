import axios from "axios";

const BASE_URL = "http://localhost:8080/api/medications";

export async function getMedications() {
  const res = await axios.get(BASE_URL);
  return res.data;
}

export async function addMedication(med: any) {
  const res = await axios.post(BASE_URL, med);
  return res.data;
}

export async function updateMedication(id: number, med: any) {
  const res = await axios.put(`${BASE_URL}/${id}`, med);
  return res.data;
}

export async function deleteMedication(id: number) {
  const res = await axios.delete(`${BASE_URL}/${id}`);
  return res.data;
}
