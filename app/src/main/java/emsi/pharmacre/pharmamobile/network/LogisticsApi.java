package emsi.pharmacre.pharmamobile.network;

import java.util.List;
import emsi.pharmacre.pharmamobile.model.Logistics;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LogisticsApi {
    @GET("/api/logistics")
    Call<List<Logistics>> getLogistics();
}