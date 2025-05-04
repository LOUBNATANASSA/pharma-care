import { useQuery } from "@tanstack/react-query";
import { getMedications } from "../services/medicationService";

export default function MedicationList() {
  const { data, isLoading, error } = useQuery({
    queryKey: ["medications"],
    queryFn: getMedications,
  });

  // Gestion du chargement
  if (isLoading) return <p>Chargement des médicaments...</p>;

  // Gestion de l'erreur
  if (error) {
    console.error("Erreur lors de la récupération des médicaments :", error);
    return <p style={{ color: "red" }}>Erreur de chargement</p>;
  }

  // Sécurité : vérifier si data est bien un tableau
  if (!Array.isArray(data)) return <p>Aucune donnée disponible.</p>;

  return (
    <ul>
      {data.map((med: any) => (
        <li key={med.id}>
          {med.name} — {med.reference}  — {med.dosage}  — {med.price} €
        </li>
      ))}
    </ul>
  );
}
