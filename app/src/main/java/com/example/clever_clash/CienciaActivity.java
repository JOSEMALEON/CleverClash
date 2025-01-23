package com.example.clever_clash;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CienciaActivity extends AppCompatActivity {

    private TextView questionBox;
    private Button btnGreen, btnYellow, btnBlue, btnRed;
    private String userName = "Usuario 1"; // Puedes recibir este valor desde la MainActivity con Intents.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciencia);

        // Configurar el nombre del usuario
        TextView userNameView = findViewById(R.id.userName);
        userNameView.setText(userName);

        // Configurar la pregunta
        questionBox = findViewById(R.id.questionBox);
        questionBox.setText("¿Qué elemento tiene como símbolo químico 'O'?");

        // Configurar los botones
        btnGreen = findViewById(R.id.btnGreen);
        btnYellow = findViewById(R.id.btnYellow);
        btnBlue = findViewById(R.id.btnBlue);
        btnRed = findViewById(R.id.btnRed);

        // Configurar eventos para los botones
        btnGreen.setOnClickListener(v -> Toast.makeText(this, "¡Correcto! 'O' es el símbolo del oxígeno.", Toast.LENGTH_SHORT).show());
        btnYellow.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, 'Oro' tiene el símbolo 'Au'.", Toast.LENGTH_SHORT).show());
        btnBlue.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, 'Oxalato' no es un elemento químico.", Toast.LENGTH_SHORT).show());
        btnRed.setOnClickListener(v -> Toast.makeText(this, "Incorrecto, 'Ósmio' tiene el símbolo 'Os'.", Toast.LENGTH_SHORT).show());
    }
}
