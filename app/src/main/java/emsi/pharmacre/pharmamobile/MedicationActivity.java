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

import emsi.pharmacre.pharmamobile.adapter.MedicationAdapter;
import emsi.pharmacre.pharmamobile.model.Medication;
import emsi.pharmacre.pharmamobile.network.MedicationApi;
import emsi.pharmacre.pharmamobile.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicationActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMedications;
    private MedicationAdapter medicationAdapter;
    private ProgressBar progressBar;
    private Button btnBack;
    private List<Medication> medicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        // Initialize views
        recyclerViewMedications = findViewById(R.id.recyclerViewMedications);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btnBack);

        // Setup RecyclerView
        recyclerViewMedications.setLayoutManager(new LinearLayoutManager(this));

        medicationList = new ArrayList<>();
        medicationAdapter = new MedicationAdapter(medicationList);
        recyclerViewMedications.setAdapter(medicationAdapter);

        // Set back button listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load data
        loadMedications();
    }

    private void loadMedications() {
        progressBar.setVisibility(View.VISIBLE);

        MedicationApi api = RetrofitClient.getInstance().create(MedicationApi.class);
        Call<List<Medication>> call = api.getMedications();

        call.enqueue(new Callback<List<Medication>>() {
            @Override
            public void onResponse(Call<List<Medication>> call, Response<List<Medication>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    medicationList.clear();
                    medicationList.addAll(response.body());
                    medicationAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MedicationActivity.this,
                            "Erreur lors du chargement des médicaments",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MedicationActivity.this,
                        "Erreur réseau: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}