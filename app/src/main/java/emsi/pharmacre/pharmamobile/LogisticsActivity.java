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

import emsi.pharmacre.pharmamobile.adapter.LogisticsAdapter;
import emsi.pharmacre.pharmamobile.model.Logistics;
import emsi.pharmacre.pharmamobile.network.LogisticsApi;
import emsi.pharmacre.pharmamobile.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogisticsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLogistics;
    private LogisticsAdapter logisticsAdapter;
    private ProgressBar progressBar;
    private Button btnBack;
    private List<Logistics> logisticsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);

        // Initialize views
        recyclerViewLogistics = findViewById(R.id.recyclerViewLots);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btnBack);

        // Setup RecyclerView
        recyclerViewLogistics.setLayoutManager(new LinearLayoutManager(this));

        logisticsList = new ArrayList<>();
        logisticsAdapter = new LogisticsAdapter(logisticsList);
        recyclerViewLogistics.setAdapter(logisticsAdapter);

        // Set back button listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load data
        loadLogistics();
    }

    private void loadLogistics() {
        progressBar.setVisibility(View.VISIBLE);

        LogisticsApi api = RetrofitClient.getInstance().create(LogisticsApi.class);
        Call<List<Logistics>> call = api.getLogistics();

        call.enqueue(new Callback<List<Logistics>>() {
            @Override
            public void onResponse(Call<List<Logistics>> call, Response<List<Logistics>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    logisticsList.clear();
                    logisticsList.addAll(response.body());
                    logisticsAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(LogisticsActivity.this,
                            "Erreur lors du chargement des données logistiques",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Logistics>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LogisticsActivity.this,
                        "Erreur réseau: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
