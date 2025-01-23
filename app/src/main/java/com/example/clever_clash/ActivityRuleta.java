package com.example.clever_clash;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityRuleta extends AppCompatActivity {

    private ImageView ruletaImage;
    private Button btnGirar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);

        ruletaImage = findViewById(R.id.ruletaImage);
        btnGirar = findViewById(R.id.btnGirar);

        btnGirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generar un ángulo de rotación aleatorio (entre 720 y 1080 grados)
                float endRotation = (float) (Math.random() * 360 + 720); // Aleatorio entre 720 y 1080 grados
                ObjectAnimator animator = ObjectAnimator.ofFloat(ruletaImage, "rotation", 0f, endRotation);
                animator.setDuration(3000); // Duración de la animación
                animator.start();

                // Redirigir o mostrar mensaje al final de la animación
                animator.addListener(new android.animation.AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(android.animation.Animator animation) {
                        super.onAnimationEnd(animation);
                        // Aquí se maneja el resultado de la ruleta
                        String selectedCategory = "";

                        // Se elige una categoría aleatoria según el ángulo final
                        if (endRotation >= 0 && endRotation < 60) {
                            selectedCategory = "Ciencia";
                        } else if (endRotation >= 60 && endRotation < 120) {
                            selectedCategory = "Historia";
                        } else if (endRotation >= 120 && endRotation < 180) {
                            selectedCategory = "Deporte";
                        } else if (endRotation >= 180 && endRotation < 240) {
                            selectedCategory = "Arte";
                        } else if (endRotation >= 240 && endRotation < 300) {
                            selectedCategory = "Entretenimiento";
                        } else if (endRotation >= 300 && endRotation < 360) {
                            selectedCategory = "Geografía";
                        } else if (endRotation >= 360 && endRotation < 420) {
                            selectedCategory = "Corona a elección"; // Sector morado
                        }

                        // Mostrar mensaje si es la categoría morada
                        if (selectedCategory.equals("Corona a elección")) {
                            Toast.makeText(getApplicationContext(), "Corona a elección", Toast.LENGTH_SHORT).show();
                        } else {
                            // Crear Intent y pasar el nombre de la categoría
                            Intent intent = new Intent(ActivityRuleta.this, CategoryActivity.class);
                            intent.putExtra("CATEGORY", selectedCategory);  // Pasamos la categoría seleccionada
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
