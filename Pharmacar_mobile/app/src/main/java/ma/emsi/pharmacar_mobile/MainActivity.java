package ma.emsi.pharmacar_mobile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.emsi.pharmacar_mobile.adapter.MedicationAdapter;
import ma.emsi.pharmacar_mobile.model.Medication;
import ma.emsi.pharmacar_mobile.network.MedicationApi;
import ma.emsi.pharmacar_mobile.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewMedications);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.GONE);

        MedicationApi service = RetrofitClient.getInstance().create(MedicationApi.class);
        Call<List<Medication>> call=service.getMedications();
        call.enqueue(new Callback<List<Medication>>() {
            @Override
            public void onResponse(Call<List<Medication>> call, Response<List<Medication>> response) {
                List<Medication> medications=response.body();
                MedicationAdapter adapter = new MedicationAdapter(medications);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable throwable) {
                Log.i("get api", throwable.getMessage());
            }
        });

    }
}