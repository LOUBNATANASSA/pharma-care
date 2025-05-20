package emsi.pharmacre.pharmamobile.network;

import java.util.List;
import emsi.pharmacre.pharmamobile.model.Lot;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LotApi {
    @GET("/api/lots")
    Call<List<Lot>> getLots();
}