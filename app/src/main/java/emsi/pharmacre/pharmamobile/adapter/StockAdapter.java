package emsi.pharmacre.pharmamobile.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import emsi.pharmacre.pharmamobile.R;
import emsi.pharmacre.pharmamobile.model.Stock;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    private final List<Stock> stockList;

    public StockAdapter(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLocation, txtAvailableQty, txtMedication, txtUpdated;

        public ViewHolder(View itemView) {
            super(itemView);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtAvailableQty = itemView.findViewById(R.id.txtAvailableQty);
            txtMedication = itemView.findViewById(R.id.txtMedication);
            txtUpdated = itemView.findViewById(R.id.txtUpdated);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        holder.txtLocation.setText("Emplacement: " + stock.location);
        holder.txtAvailableQty.setText("Quantité disponible: " + stock.availableQuantity);
        if (stock.lot != null && stock.lot.medication != null) {
            holder.txtMedication.setText("Médicament: " + stock.lot.medication.name);
        } else {
            holder.txtMedication.setText("Médicament: N/A");
        }
        holder.txtUpdated.setText("Dernière mise à jour: " + stock.lastUpdated);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }
}