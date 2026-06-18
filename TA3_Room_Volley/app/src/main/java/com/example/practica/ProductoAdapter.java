package com.example.practica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private final List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.tvNombre.setText(producto.getNombre());
        
        // Format price as S/ 0.00
        String precioFormateado = String.format(Locale.US, "S/ %.2f", producto.getPrecio());
        holder.tvPrecio.setText(precioFormateado);
        holder.ivImagen.setImageResource(producto.getImagenResId());

        holder.itemView.setOnClickListener(v -> {
            String mensaje = "Seleccionaste: " + producto.getNombre() + " — " + precioFormateado;
            Toast.makeText(v.getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivImagen;
        final TextView tvNombre, tvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.iv_producto_imagen);
            tvNombre = itemView.findViewById(R.id.tv_producto_nombre);
            tvPrecio = itemView.findViewById(R.id.tv_producto_precio);
        }
    }
}
