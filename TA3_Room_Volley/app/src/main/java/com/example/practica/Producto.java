package com.example.practica;

public class Producto {
    private final String nombre;
    private final double precio;
    private final int imagenResId;

    public Producto(String nombre, double precio, int imagenResId) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
