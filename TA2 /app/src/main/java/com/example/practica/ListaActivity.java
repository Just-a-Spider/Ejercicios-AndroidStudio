package com.example.practica;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_EDIT_FROM_LIST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_list));
        }

        RecyclerView rvUsuarios = findViewById(R.id.rv_usuarios);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));

        List<Usuario> listaUsuarios = UserManager.getInstance().getListaUsuarios();

        UsuarioAdapter adapter = new UsuarioAdapter(listaUsuarios, usuario -> {
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra("usuario", usuario);
            startActivityForResult(intent, REQUEST_CODE_EDIT_FROM_LIST);
        });
        rvUsuarios.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDIT_FROM_LIST && resultCode == RESULT_OK && data != null) {
            setResult(RESULT_OK, data);
            finish();
        }
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
            finish();
            return true;
        } else if (id == R.id.action_list_users) {
            Toast.makeText(this, "Ya estás viendo la lista", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_logout) {
            Toast.makeText(this, getString(R.string.logout_toast), Toast.LENGTH_SHORT).show();
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}