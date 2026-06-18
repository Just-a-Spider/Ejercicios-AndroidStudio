package com.example.practica;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Lista de Productos");
        }

        RecyclerView rvProductos = findViewById(R.id.rv_productos);
        rvProductos.setLayoutManager(new LinearLayoutManager(this));

        // ponytail: Load static list of 6 products with system drawables
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Laptop Pro", 3499.99, android.R.drawable.ic_menu_gallery));
        productos.add(new Producto("Smartphone X", 1899.50, android.R.drawable.ic_menu_camera));
        productos.add(new Producto("Smartwatch v3", 650.00, android.R.drawable.ic_menu_manage));
        productos.add(new Producto("Auriculares Bluetooth", 299.90, android.R.drawable.ic_menu_search));
        productos.add(new Producto("Teclado Mecánico", 180.00, android.R.drawable.ic_menu_share));
        productos.add(new Producto("Mouse Inalámbrico", 120.00, android.R.drawable.ic_menu_send));

        ProductoAdapter adapter = new ProductoAdapter(productos);
        rvProductos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_tareas) {
            startActivity(new Intent(this, TareasActivity.class));
            return true;
        } else if (id == R.id.action_usuarios_api) {
            startActivity(new Intent(this, UsuariosApiActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
