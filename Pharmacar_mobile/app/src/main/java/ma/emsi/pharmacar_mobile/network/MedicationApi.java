package ma.emsi.pharmacar_mobile.network;


import java.util.List;

import ma.emsi.pharmacar_mobile.model.Medication;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MedicationApi {
    @GET("/api/medications")
    Call<List<Medication>> getMedications();

}
