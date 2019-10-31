package com.example.inthujan.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ArtistViewHolder> {

    private Context mCtx;
    private List<Bus> busList;
    private ItemClickListener clickListener;

    public BusAdapter(Context mCtx, List<Bus> busList) {
        this.mCtx = mCtx;
        this.busList = busList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_buses, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Bus bus = busList.get(position);
        holder.textViewBusName.setText(bus.travelsName);
        holder.textViewBusNumber.setText("Bus Number : " + bus.busNumber);
        holder.textViewDate.setText("Journey Date : " + bus.date);
        holder.textViewFrom.setText("From : " + bus.from);
        holder.textViewTo.setText("To : " + bus.to);
        holder.textViewCondition.setText("Bus Condition: " + bus.busCondition);
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewBusName, textViewBusNumber, textViewDate, textViewFrom,textViewTo,textViewCondition;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewBusName = itemView.findViewById(R.id.text_view_busName);
            textViewBusNumber = itemView.findViewById(R.id.text_view_busNumber);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewFrom = itemView.findViewById(R.id.text_view_from);
            textViewTo = itemView.findViewById(R.id.text_view_to);
            textViewCondition = itemView.findViewById(R.id.text_view_condition);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}

