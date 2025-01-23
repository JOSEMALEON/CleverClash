package com.example.clever_clash;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ArtActivity extends AppCompatActivity {

    private TextView questionBox;
    private Button btnGreen, btnYellow, btnBlue, btnRed;
    private String userName = "Usuario 1"; // Puedes recibir este valor desde la MainActivity con Intents.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arte);

        // Configurar el nombre del usuario
        TextView userNameView = findViewById(R.id.userName);
        userNameView.setText(userName);

        // Configurar la pregunta
        questionBox = findViewById(R.id.questionBox);
        questionBox.setText("¿Quién pintó La Última Cena?");

        // Configurar los botones
        btnGreen = findViewById(R.id.btnGreen);
        btnYellow = findViewById(R.id.btnYellow);
        btnBlue = findViewById(R.id.btnBlue);
        btnRed = findViewById(R.id.btnRed);

        // Configurar eventos para los botones
        btnGreen.setOnClickListener(v -> Toast.makeText(this, "Respuesta: Miguel Ángel", Toast.LENGTH_SHORT).show());
        btnYellow.setOnClickListener(v -> Toast.makeText(this, "Respuesta: Da Vinci", Toast.LENGTH_SHORT).show());
        btnBlue.setOnClickListener(v -> Toast.makeText(this, "Respuesta: Picasso", Toast.LENGTH_SHORT).show());
        btnRed.setOnClickListener(v -> Toast.makeText(this, "Respuesta: Velázquez", Toast.LENGTH_SHORT).show());
    }
}
