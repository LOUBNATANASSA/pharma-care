package emsi.pharmacre.pharmamobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import emsi.pharmacre.pharmamobile.R;
import emsi.pharmacre.pharmamobile.model.Lot;

public class LotAdapter extends RecyclerView.Adapter<LotAdapter.ViewHolder> {

    private final List<Lot> lotList;

    public LotAdapter(List<Lot> lotList) {
        this.lotList = lotList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLotNumber, txtExpiration, txtQuantity, txtMedication;

        public ViewHolder(View itemView) {
            super(itemView);
            txtLotNumber = itemView.findViewById(R.id.txtLotNumber);
            txtExpiration = itemView.findViewById(R.id.txtExpiration);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtMedication = itemView.findViewById(R.id.txtMedication);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lot lot = lotList.get(position);
        holder.txtLotNumber.setText("Numéro de lot: " + lot.lotNumber);
        holder.txtExpiration.setText("Date d'expiration: " + lot.expirationDate);
        holder.txtQuantity.setText("Quantité: " + lot.quantity);
        holder.txtMedication.setText("Médicament: " + (lot.medication != null ? lot.medication.name : "N/A"));
    }

    @Override
    public int getItemCount() {
        return lotList.size();
    }
}