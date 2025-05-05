package com.example.mobile.network;
import com.example.mobile.model.Medication;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MedicationApi {
    @GET("/api/medications")
    Call<List<Medication>> getMedications();

}
