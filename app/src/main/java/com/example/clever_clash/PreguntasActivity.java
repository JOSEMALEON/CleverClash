package com.example.clever_clash;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PreguntasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        // Obtener el nombre de la categoría pasada desde VisorPreguntasActivity
        String categoria = getIntent().getStringExtra("categoria");

        // Mostrar la categoría en un TextView
        TextView textView = findViewById(R.id.textViewCategoria);
        textView.setText("Categoría seleccionada: " + categoria);
    }
}
