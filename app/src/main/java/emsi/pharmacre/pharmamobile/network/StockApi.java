package emsi.pharmacre.pharmamobile.network;

import java.util.List;
import emsi.pharmacre.pharmamobile.model.Stock;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StockApi {
    @GET("/api/stocks")
    Call<List<Stock>> getStocks();
}