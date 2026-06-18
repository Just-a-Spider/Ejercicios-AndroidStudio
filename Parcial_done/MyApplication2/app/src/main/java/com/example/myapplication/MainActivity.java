package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_MAIN = 1;

    private EditText etPeso, etAltura;

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

        etPeso = findViewById(R.id.et_peso);
        etAltura = findViewById(R.id.et_altura);
        Button btnEnviar = findViewById(R.id.btn_enviar);

        btnEnviar.setOnClickListener(v -> {
            String peso = etPeso.getText().toString();
            String altura = etAltura.getText().toString();

            if (peso.isEmpty() || altura.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            IMC imc = new IMC(Float.parseFloat(peso), Float.parseFloat(altura));

            IMCManager.getInstance().addIMC(imc);
            Toast.makeText(this, "IMC guardado correctamente", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("imc", imc);
            startActivityForResult(intent, REQUEST_CODE_MAIN);
        });
    }
}