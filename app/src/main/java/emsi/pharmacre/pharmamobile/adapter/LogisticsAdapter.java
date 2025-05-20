package emsi.pharmacre.pharmamobile.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import emsi.pharmacre.pharmamobile.R;
import emsi.pharmacre.pharmamobile.model.Logistics;

public class LogisticsAdapter extends RecyclerView.Adapter<LogisticsAdapter.ViewHolder> {

    private final List<Logistics> logisticsList;

    public LogisticsAdapter(List<Logistics> logisticsList) {
        this.logisticsList = logisticsList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOperationType, txtDate, txtQuantity, txtSource, txtDestination;

        public ViewHolder(View itemView) {
            super(itemView);
            txtOperationType = itemView.findViewById(R.id.txtOperationType);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtSource = itemView.findViewById(R.id.txtSource);
            txtDestination = itemView.findViewById(R.id.txtDestination);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_logistics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Logistics logistics = logisticsList.get(position);
        holder.txtOperationType.setText("Type d'opération: " + logistics.operationType);
        holder.txtDate.setText("Date: " + logistics.date);
        holder.txtQuantity.setText("Quantité: " + logistics.quantity);
        holder.txtSource.setText("Source: " + logistics.source);
        holder.txtDestination.setText("Destination: " + logistics.destination);
    }

    @Override
    public int getItemCount() {
        return logisticsList.size();
    }
}