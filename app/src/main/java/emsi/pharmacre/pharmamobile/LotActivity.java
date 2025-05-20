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

import emsi.pharmacre.pharmamobile.adapter.LotAdapter;
import emsi.pharmacre.pharmamobile.model.Lot;
import emsi.pharmacre.pharmamobile.network.LotApi;
import emsi.pharmacre.pharmamobile.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LotActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLots;
    private LotAdapter lotAdapter;
    private ProgressBar progressBar;
    private Button btnBack;
    private List<Lot> lotList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot);

        // Initialize views
        recyclerViewLots = findViewById(R.id.recyclerViewLots);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btnBack);

        // Setup RecyclerView
        recyclerViewLots.setLayoutManager(new LinearLayoutManager(this));

        lotList = new ArrayList<>();
        lotAdapter = new LotAdapter(lotList);
        recyclerViewLots.setAdapter(lotAdapter);

        // Set back button listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load data
        loadLots();
    }

    private void loadLots() {
        progressBar.setVisibility(View.VISIBLE);

        LotApi api = RetrofitClient.getInstance().create(LotApi.class);
        Call<List<Lot>> call = api.getLots();

        call.enqueue(new Callback<List<Lot>>() {
            @Override
            public void onResponse(Call<List<Lot>> call, Response<List<Lot>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    lotList.clear();
                    lotList.addAll(response.body());
                    lotAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(LotActivity.this,
                            "Erreur lors du chargement des lots",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Lot>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LotActivity.this,
                        "Erreur réseau: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}