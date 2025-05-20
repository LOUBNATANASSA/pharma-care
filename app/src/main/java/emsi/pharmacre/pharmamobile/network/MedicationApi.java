package emsi.pharmacre.pharmamobile.network;

import java.util.List;
import emsi.pharmacre.pharmamobile.model.Medication;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MedicationApi {
    @GET("/api/medications")
    Call<List<Medication>> getMedications();
}