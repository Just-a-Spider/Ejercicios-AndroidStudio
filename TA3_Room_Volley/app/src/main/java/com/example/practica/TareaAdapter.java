package com.example.practica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private final List<Tarea> tareas;
    private final OnTareaLongClickListener longClickListener;

    public interface OnTareaLongClickListener {
        void onTareaLongClick(Tarea tarea);
    }

    public TareaAdapter(List<Tarea> tareas, OnTareaLongClickListener longClickListener) {
        this.tareas = tareas;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = tareas.get(position);
        holder.tvTitulo.setText(tarea.titulo);
        holder.tvDescripcion.setText(tarea.descripcion);

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onTareaLongClick(tarea);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitulo;
        final TextView tvDescripcion;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tv_tarea_titulo);
            tvDescripcion = itemView.findViewById(R.id.tv_tarea_descripcion);
        }
    }
}
