package com.example.clever_clash;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Obtener el nombre de la categoría desde el Intent
        String category = getIntent().getStringExtra("CATEGORY");

        // Mostrar la categoría en un TextView (o usarla para redirigir a más actividades)
        TextView categoryTextView = findViewById(R.id.categoryTextView);
        categoryTextView.setText("Categoría seleccionada: " + category);

        // Aquí podrías redirigir a actividades específicas según la categoría
        if (category.equals("Ciencia")) {
            // Redirigir o mostrar preguntas de Ciencia
        } else if (category.equals("Historia")) {
            // Redirigir o mostrar preguntas de Historia
        } else if (category.equals("Deporte")) {
            // Redirigir o mostrar preguntas de Deporte
        } else if (category.equals("Arte")) {
            // Redirigir o mostrar preguntas de Arte
        } else if (category.equals("Entretenimiento")) {
            // Redirigir o mostrar preguntas de Entretenimiento
        } else if (category.equals("Geografia")) {
            // Redirigir o mostrar preguntas de Geografía
        }
    }
}
