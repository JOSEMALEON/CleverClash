package com.example.clever_clash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class RuletaActivity extends AppCompatActivity {

    private RuletaDrawable ruletaDrawable;
    private ImageView ruletaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);

        ruletaImageView = findViewById(R.id.ruletaImage);
        Button btnGirar = findViewById(R.id.btnGirar);

        // Inicializar la ruleta
        ruletaDrawable = new RuletaDrawable();
        ruletaImageView.setImageDrawable(ruletaDrawable);

        // Configurar el botón para girar la ruleta
        btnGirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruletaDrawable.girar();
            }
        });
    }
}