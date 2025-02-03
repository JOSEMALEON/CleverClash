package com.example.clever_clash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ActivityRuleta extends AppCompatActivity {

    private RuletaDrawable ruletaDrawable;
    private Button btnGirar;
    private boolean isSpinning = false;
    private final int SECTOR_COUNT = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);

        ImageView ruletaImage = findViewById(R.id.ruletaImage);
        btnGirar = findViewById(R.id.btnGirar);

        ruletaDrawable = new RuletaDrawable(this);
        ruletaImage.setImageDrawable(ruletaDrawable);

        btnGirar.setOnClickListener(v -> {
            if (!isSpinning) {
                girarRuleta();
            }
        });
    }

    private void girarRuleta() {
        isSpinning = true;
        btnGirar.setEnabled(false);

        int sectorSeleccionado = new Random().nextInt(SECTOR_COUNT);
        float sectorAngle = 360f / SECTOR_COUNT;

        // Calculamos el ángulo final para que el sector quede bajo el indicador
        float targetAngle = (sectorSeleccionado * sectorAngle) + (sectorAngle / 2);
        float rotationAmount = 1080 + targetAngle + (new Random().nextFloat() * 720);

        ObjectAnimator animator = ObjectAnimator.ofFloat(
                ruletaDrawable,
                "rotation",
                ruletaDrawable.getRotation(),
                ruletaDrawable.getRotation() + rotationAmount
        );

        animator.setDuration(4000);
        animator.setInterpolator(new DecelerateInterpolator(1.5f));

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isSpinning = false;
                btnGirar.setEnabled(true);

                String selectedCategory = getCategoryFromSector(sectorSeleccionado);
                Toast.makeText(ActivityRuleta.this, "¡Ha tocado el sector: " + selectedCategory + "!", Toast.LENGTH_SHORT).show();

                if (!selectedCategory.equals("Corona a elección")) {
                    Intent intent = new Intent(ActivityRuleta.this, CategoryActivity.class);
                    intent.putExtra("CATEGORY", selectedCategory);
                    startActivity(intent);
                }
            }
        });

        animator.start();
    }

    private String getCategoryFromSector(int sector) {
        String[] categories = {
                "Ciencia",
                "Historia",
                "Deporte",
                "Arte",
                "Entretenimiento",
                "Geografía",
                "Corona a elección"
        };
        return categories[sector];
    }
}
