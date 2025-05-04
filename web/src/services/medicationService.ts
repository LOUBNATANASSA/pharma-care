import axios from "axios";



export async function getMedications() {
  const response = await axios.get("http://localhost:8080/api/medications");
  return response.data;
}

export async function addMedication(med: any) {
    const res = await axios.post("http://localhost:8080/api/medications", med);
    return res.data;
  }
