package com.example.clever_clash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class VisorPreguntasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_preguntas);

        // Obtener los botones del layout
        Button btnCiencia = findViewById(R.id.btnCiencia);
        Button btnHistoria = findViewById(R.id.btnHistoria);
        Button btnDeporte = findViewById(R.id.btnDeporte);
        Button btnArte = findViewById(R.id.btnArte);
        Button btnEntretenimiento = findViewById(R.id.btnEntretenimiento);
        Button btnGeografia = findViewById(R.id.btnGeografia);
        Button btnCorona = findViewById(R.id.btnCorona);

        // Asignar eventos a los botones para abrir sus respectivas actividades
        btnCiencia.setOnClickListener(v -> abrirActividad(CienciaActivity.class));
        btnHistoria.setOnClickListener(v -> abrirActividad(HistoriaActivity.class));
        btnDeporte.setOnClickListener(v -> abrirActividad(SportsActivity.class));
        btnArte.setOnClickListener(v -> abrirActividad(ArtActivity.class));
        btnEntretenimiento.setOnClickListener(v -> abrirActividad(EntretenimientoActivity.class));
        btnGeografia.setOnClickListener(v -> abrirActividad(GeografiaActivity.class));
        btnCorona.setOnClickListener(v -> abrirActividad(CoronaActivity.class));
    }

    // Método para iniciar una actividad
    private void abrirActividad(Class<?> actividad) {
        Intent intent = new Intent(VisorPreguntasActivity.this, actividad);
        startActivity(intent);
    }
}
