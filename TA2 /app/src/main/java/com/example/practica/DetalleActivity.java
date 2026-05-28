package com.example.practica;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetalleActivity extends AppCompatActivity {
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_detail));
        }

        TextView tvNombre = findViewById(R.id.tv_nombre_valor);
        TextView tvEdad = findViewById(R.id.tv_edad_valor);
        TextView tvCorreo = findViewById(R.id.tv_correo_valor);
        Button btnEditar = findViewById(R.id.btn_editar);
        Button btnLlamar = findViewById(R.id.btn_llamar);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        if (usuario != null) {
            tvNombre.setText(usuario.getNombre());
            tvEdad.setText(String.valueOf(usuario.getEdad()));
            tvCorreo.setText(usuario.getCorreo());
        }

        btnEditar.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("usuario", usuario);
            setResult(RESULT_OK, returnIntent);
            finish();
        });

        btnLlamar.setOnClickListener(v -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:999999999"));
            startActivity(dialIntent);
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
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_list_users) {
            startActivity(new Intent(this, ListaActivity.class));
            finish();
            return true;
        } else if (id == R.id.action_logout) {
            Toast.makeText(this, getString(R.string.logout_toast), Toast.LENGTH_SHORT).show();
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
