package com.example.practica;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_EDIT = 1;
    private static final int REQUEST_CODE_LIST = 2;

    private EditText etNombre, etEdad, etCorreo;

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

        etNombre = findViewById(R.id.et_nombre);
        etEdad = findViewById(R.id.et_edad);
        etCorreo = findViewById(R.id.et_correo);
        Button btnEnviar = findViewById(R.id.btn_enviar);

        btnEnviar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String edadStr = etEdad.getText().toString();
            String correo = etCorreo.getText().toString();

            if (nombre.isEmpty() || edadStr.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int edad = Integer.parseInt(edadStr);
            Usuario usuario = new Usuario(nombre, edad, correo);

            UserManager.getInstance().addUsuario(usuario);
            Toast.makeText(this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra("usuario", usuario);
            startActivityForResult(intent, REQUEST_CODE_EDIT);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_user) {
            etNombre.setText("");
            etEdad.setText("");
            etCorreo.setText("");
            Toast.makeText(this, "Formulario limpiado", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_list_users) {
            Intent intent = new Intent(this, ListaActivity.class);
            startActivityForResult(intent, REQUEST_CODE_LIST);
            return true;
        } else if (id == R.id.action_logout) {
            Toast.makeText(this, getString(R.string.logout_toast), Toast.LENGTH_SHORT).show();
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_CODE_EDIT || requestCode == REQUEST_CODE_LIST) 
                && resultCode == RESULT_OK && data != null) {
            Usuario usuario = (Usuario) data.getSerializableExtra("usuario");
            if (usuario != null) {
                etNombre.setText(usuario.getNombre());
                etEdad.setText(String.valueOf(usuario.getEdad()));
                etCorreo.setText(usuario.getCorreo());
            }
        }
    }
}
