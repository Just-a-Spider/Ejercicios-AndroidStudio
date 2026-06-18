package com.example.practica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TareasActivity extends AppCompatActivity {

    private EditText etTitulo, etDescripcion;
    private AppDatabase db;
    private TareaAdapter adapter;
    private final List<Tarea> tareasList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Gestión de Tareas");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = AppDatabase.getDatabase(this);

        etTitulo = findViewById(R.id.et_tarea_titulo);
        etDescripcion = findViewById(R.id.et_tarea_descripcion);
        Button btnGuardar = findViewById(R.id.btn_guardar_tarea);
        RecyclerView rvTareas = findViewById(R.id.rv_tareas);

        rvTareas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TareaAdapter(tareasList, tarea -> {
            // Delete task in background thread
            AppDatabase.databaseExecutor.execute(() -> {
                db.tareaDao().eliminar(tarea);
                runOnUiThread(() -> {
                    Toast.makeText(TareasActivity.this, "Tarea eliminada: " + tarea.titulo, Toast.LENGTH_SHORT).show();
                    loadTareas();
                });
            });
        });
        rvTareas.setAdapter(adapter);

        btnGuardar.setOnClickListener(v -> {
            String titulo = etTitulo.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();

            if (titulo.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Tarea nuevaTarea = new Tarea();
            nuevaTarea.titulo = titulo;
            nuevaTarea.descripcion = descripcion;
            nuevaTarea.completada = false;

            AppDatabase.databaseExecutor.execute(() -> {
                db.tareaDao().insertar(nuevaTarea);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show();
                    etTitulo.setText("");
                    etDescripcion.setText("");
                    loadTareas();
                });
            });
        });

        loadTareas();
    }

    private void loadTareas() {
        AppDatabase.databaseExecutor.execute(() -> {
            List<Tarea> lista = db.tareaDao().obtenerTodas();
            runOnUiThread(() -> {
                tareasList.clear();
                tareasList.addAll(lista);
                adapter.notifyDataSetChanged();
            });
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
