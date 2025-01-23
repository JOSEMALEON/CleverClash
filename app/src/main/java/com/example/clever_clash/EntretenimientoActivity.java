package com.example.clever_clash;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EntretenimientoActivity extends AppCompatActivity {

    private TextView questionBox;
    private Button btnGreen, btnYellow, btnBlue, btnRed;
    private String userName = "Usuario 1"; // Esto se puede pasar desde la MainActivity con Intents

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretenimiento);

        // Configurar el nombre del usuario
        TextView userNameView = findViewById(R.id.userName);
        userNameView.setText(userName);

        // Configurar la pregunta
        questionBox = findViewById(R.id.questionBox);
        questionBox.setText("¿Cuál es la película más taquillera de la historia?");

        // Configurar los botones
        btnGreen = findViewById(R.id.btnGreen);
        btnYellow = findViewById(R.id.btnYellow);
        btnBlue = findViewById(R.id.btnBlue);
        btnRed = findViewById(R.id.btnRed);

        // Configurar eventos para los botones
        btnGreen.setOnClickListener(v -> Toast.makeText(this, "Respuesta: Avatar", Toast.LENGTH_SHORT).show());
        btnYellow.setOnClickListener(v -> Toast.makeText(this, "Respuesta: Avengers", Toast.LENGTH_SHORT).show());
        btnBlue.setOnClickListener(v -> Toast.makeText(this, "Respuesta: Titanic", Toast.LENGTH_SHORT).show());
        btnRed.setOnClickListener(v -> Toast.makeText(this, "Respuesta: Star Wars", Toast.LENGTH_SHORT).show());
    }
}
