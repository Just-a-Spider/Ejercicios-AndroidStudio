package com.example.myapplication;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class IMCAdapter extends RecyclerView.Adapter<IMCAdapter.IMCViewHolder> {
    private final List<IMC> listaIMC;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(IMC IMC);
    }

    public IMCAdapter(List<IMC> listaIMC, OnItemClickListener listener) {
        this.listaIMC = listaIMC;
        this.listener = listener;
    }

    @NonNull
    @Override
    public IMCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imc, parent, false);
        return new IMCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IMCViewHolder holder, int position) {
        IMC IMC = listaIMC.get(position);
        holder.tvPeso.setText(IMC.getPeso().toString());
        holder.tvAltura.setText((IMC.getAltura().toString()));
        holder.tvIMC.setText(IMC.getImc().toString());
    }

    @Override
    public int getItemCount() {
        return listaIMC.size();
    }

    public static class IMCViewHolder extends RecyclerView.ViewHolder {
        TextView tvPeso, tvAltura, tvIMC;

        public IMCViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPeso = itemView.findViewById(R.id.tv_item_peso);
            tvAltura = itemView.findViewById(R.id.tv_item_altura);
            tvIMC = itemView.findViewById(R.id.tv_ver_imc);
        }
    }

}
