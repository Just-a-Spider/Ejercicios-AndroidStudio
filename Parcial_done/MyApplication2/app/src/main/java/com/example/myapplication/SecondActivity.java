package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_EDIT_FROM_LIST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        RecyclerView rvIMC = findViewById(R.id.rv_IMC);
        rvIMC.setLayoutManager(new LinearLayoutManager(this));

        List<IMC> listaIMC = IMCManager.getInstance().getListaIMC();

        IMCAdapter adapter = new IMCAdapter(listaIMC, imc -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("imc", imc);
            startActivityForResult(intent, REQUEST_CODE_EDIT_FROM_LIST);
        });
        rvIMC.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}