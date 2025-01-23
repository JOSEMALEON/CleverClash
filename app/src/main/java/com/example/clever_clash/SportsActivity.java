package com.example.clever_clash;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SportsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        // Mensaje de prueba
        Toast.makeText(this, "Categoría: Deportes", Toast.LENGTH_SHORT).show();
    }
}
