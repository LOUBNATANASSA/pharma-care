package com.example.mobile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.adapter.MedicationAdapter;
import com.example.mobile.model.Medication;
import com.example.mobile.network.MedicationApi;
import com.example.mobile.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

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

        // ⚠ Données manuelles
        List<Medication> list = new ArrayList<>();
        Medication m = new Medication();
        m.name = "Doliprane";
        m.price = 50;
        list.add(m);

        Medication m2 = new Medication();
        m2.name = "Efferalgan";
        m2.price = 30;
        list.add(m2);

        Medication m3 = new Medication();
        m3.name = "Efferalgan";
        m3.price = 30;
        list.add(m3);


        Medication m4 = new Medication();
        m4.name = "Efferalgan";
        m4.price = 30;
        list.add(m4);

        Medication m5 = new Medication();
        m5.name = "Efferalgan";
        m5.price = 30;
        list.add(m5);

        Medication m6 = new Medication();
        m6.name = "Efferalgan";
        m6.price = 30;
        list.add(m6);

        Medication m61 = new Medication();
        m61.name = "Efferalgan";
        m61.price = 30;
        list.add(m61);

        Medication m31 = new Medication();
        m31.name = "Efferalgan";
        m31.price = 30;
        list.add(m31);


        Medication m32 = new Medication();
        m32.name = "Efferalgan";
        m32.price = 30;
        list.add(m32);

        Medication m33 = new Medication();
        m33.name = "Efferalgan";
        m33.price = 30;
        list.add(m33);

        Medication m34 = new Medication();
        m34.name = "Efferalgan";
        m34.price = 30;
        list.add(m34);

        Medication m35 = new Medication();
        m35.name = "Efferalgan";
        m35.price = 30;
        list.add(m35);

        Medication m36 = new Medication();
        m36.name = "Efferalgan";
        m36.price = 30;
        list.add(m36);

        Medication m37 = new Medication();
        m37.name = "Efferalgan";
        m37.price = 30;
        list.add(m37);

        Medication m371 = new Medication();
        m371.name = "ROVAMICINE";
        m371.price = 200;
        list.add(m371);

        recyclerView.setAdapter(new MedicationAdapter(list));

    }
}
