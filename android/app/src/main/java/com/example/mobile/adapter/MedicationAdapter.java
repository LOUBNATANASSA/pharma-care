package com.example.mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.R;
import com.example.mobile.model.Medication;
import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.ViewHolder> {

    private final List<Medication> medicationList;

    public MedicationAdapter(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }

    @NonNull
    @Override
    public MedicationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medication, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationAdapter.ViewHolder holder, int position) {
        Medication m = medicationList.get(position);
        holder.txtName.setText(m.name);
        holder.txtQuantity.setText("Price : " + m.price);
    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }
}