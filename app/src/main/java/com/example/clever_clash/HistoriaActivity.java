package com.example.clever_clash;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HistoriaActivity extends AppCompatActivity {

    private TextView questionBox;
    private Button btnGreen, btnYellow, btnBlue, btnRed;
    private String userName = "Usuario 1"; // Puedes recibir este valor desde la MainActivity con Intents.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

        // Configurar el nombre del usuario
        TextView userNameView = findViewById(R.id.userName);
        userNameView.setText(userName);

        // Configurar la pregunta
        questionBox = findViewById(R.id.questionBox);
        questionBox.setText("¿En qué año comenzó la Segunda Guerra Mundial?");

        // Configurar los botones
        btnGreen = findViewById(R.id.btnGreen);
        btnYellow = findViewById(R.id.btnYellow);
        btnBlue = findViewById(R.id.btnBlue);
        btnRed = findViewById(R.id.btnRed);

        // Configurar eventos para los botones
        btnGreen.setOnClickListener(v -> Toast.makeText(this, "¡Correcto! 1939", Toast.LENGTH_SHORT).show());
        btnYellow.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, 1945 es el fin.", Toast.LENGTH_SHORT).show());
        btnBlue.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, 1914 es la Primera Guerra Mundial.", Toast.LENGTH_SHORT).show());
        btnRed.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, 1960 no es correcto.", Toast.LENGTH_SHORT).show());
    }
}
