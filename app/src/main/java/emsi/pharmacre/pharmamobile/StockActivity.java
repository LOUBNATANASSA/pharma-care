package emsi.pharmacre.pharmamobile;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import emsi.pharmacre.pharmamobile.adapter.StockAdapter;
import emsi.pharmacre.pharmamobile.model.Stock;
import emsi.pharmacre.pharmamobile.network.StockApi;
import emsi.pharmacre.pharmamobile.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockActivity extends AppCompatActivity {

    private RecyclerView recyclerViewStocks;
    private StockAdapter stockAdapter;
    private ProgressBar progressBar;
    private Button btnBack;
    private List<Stock> stockList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        // Initialize views
        recyclerViewStocks = findViewById(R.id.recyclerViewLots);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btnBack);

        // Setup RecyclerView
        recyclerViewStocks.setLayoutManager(new LinearLayoutManager(this));

        stockList = new ArrayList<>();
        stockAdapter = new StockAdapter(stockList);
        recyclerViewStocks.setAdapter(stockAdapter);

        // Set back button listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load data
        loadStocks();
    }

    private void loadStocks() {
        progressBar.setVisibility(View.VISIBLE);

        StockApi api = RetrofitClient.getInstance().create(StockApi.class);
        Call<List<Stock>> call = api.getStocks();

        call.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    stockList.clear();
                    stockList.addAll(response.body());
                    stockAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(StockActivity.this,
                            "Erreur lors du chargement des stocks",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(StockActivity.this,
                        "Erreur réseau: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}