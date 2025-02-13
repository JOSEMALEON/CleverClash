package com.example.clever_clash;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CoronaActivity extends AppCompatActivity {

    private TextView questionBox;
    private Button btnGreen, btnYellow, btnBlue, btnRed;
    private String userName = "Usuario 1"; // Puedes recibir este valor desde la MainActivity con Intents.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);

        // Configurar el nombre del usuario
        TextView userNameView = findViewById(R.id.userName);
        userNameView.setText(userName);

        // Configurar la pregunta
        questionBox = findViewById(R.id.questionBox);
        questionBox.setText("¿Qué corona es la más antigua del mundo?");

        // Configurar los botones
        btnGreen = findViewById(R.id.btnGreen);
        btnYellow = findViewById(R.id.btnYellow);
        btnBlue = findViewById(R.id.btnBlue);
        btnRed = findViewById(R.id.btnRed);

        // Configurar eventos para los botones
        btnGreen.setOnClickListener(v -> Toast.makeText(this, "¡Correcto! La corona de hierro es la más antigua.", Toast.LENGTH_SHORT).show());
        btnYellow.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, la corona de San Eduardo es más reciente.", Toast.LENGTH_SHORT).show());
        btnBlue.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, la corona imperial de Rusia es más nueva.", Toast.LENGTH_SHORT).show());
        btnRed.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, la corona de España no es la más antigua.", Toast.LENGTH_SHORT).show());
    }
}
