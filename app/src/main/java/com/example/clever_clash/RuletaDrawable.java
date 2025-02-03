package com.example.clever_clash;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;
import java.util.Random;

public class RuletaDrawable extends Drawable {
    private Paint paint;
    private Paint indicatorPaint;
    private float currentRotation = 0;
    private int[] colors = {
            Color.parseColor("#4CAF50"), // Ciencia (Verde)
            Color.parseColor("#FFEB3B"), // Historia (Amarillo)
            Color.parseColor("#FF9800"), // Deporte (Naranja)
            Color.parseColor("#F44336"), // Arte (Rojo)
            Color.parseColor("#E91E63"), // Entretenimiento (Rosa)
            Color.parseColor("#2196F3"), // Geografía (Azul)
            Color.parseColor("#9C27B0")  // Corona a elección (Morado)
    };

    private Context context;

    public RuletaDrawable(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        indicatorPaint = new Paint();
        indicatorPaint.setColor(Color.BLACK);
        indicatorPaint.setStyle(Paint.Style.FILL);
        indicatorPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float centerX = width / 2f;
        float centerY = height / 2f;
        float radius = Math.min(width, height) / 2f;

        // Guardar el estado actual del canvas
        canvas.save();

        // Rotar el canvas desde el centro
        canvas.rotate(currentRotation, centerX, centerY);

        RectF rect = new RectF(centerX - radius, centerY - radius,
                centerX + radius, centerY + radius);

        float sweepAngle = 360f / colors.length;
        float startAngle = 0;

        // Dibujar los sectores
        for (int i = 0; i < colors.length; i++) {
            paint.setColor(colors[i]);
            canvas.drawArc(rect, startAngle, sweepAngle, true, paint);
            startAngle += sweepAngle;
        }

        // Restaurar el canvas
        canvas.restore();

        // Dibujar el indicador fijo
        float indicatorSize = radius * 0.15f;
        Path indicatorPath = new Path();
        indicatorPath.moveTo(centerX, centerY - radius);
        indicatorPath.lineTo(centerX - indicatorSize, centerY - radius + indicatorSize);
        indicatorPath.lineTo(centerX + indicatorSize, centerY - radius + indicatorSize);
        indicatorPath.close();
        canvas.drawPath(indicatorPath, indicatorPaint);
    }

    public void setRotation(float rotation) {
        this.currentRotation = rotation;
        invalidateSelf();
    }

    public void girar() {
        Random random = new Random();
        float giroFinal = random.nextInt(360) + (360 * 5); // Gira al menos 5 vueltas completas

        ValueAnimator animator = ValueAnimator.ofFloat(currentRotation, currentRotation + giroFinal);
        animator.setDuration(3000); // Duración de la animación (3 segundos)
        animator.setInterpolator(new DecelerateInterpolator()); // Hace que frene suavemente
        animator.addUpdateListener(animation -> {
            setRotation((float) animation.getAnimatedValue());
        });

        animator.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                mostrarResultado((currentRotation + giroFinal) % 360); // Obtener ángulo final normalizado
            }
        });

        animator.start();
    }

    private void mostrarResultado(float anguloFinal) {
        // Ajustar para que el ángulo 0° esté en la parte superior
        anguloFinal = (anguloFinal + 360 - (360 / colors.length) / 2) % 360;

        // Determinar el sector
        int sector = (int) (anguloFinal / (360 / colors.length));

        // Mensaje asociado a cada sector
        String[] categorias = {
                "Ciencia (Verde)",
                "Historia (Amarillo)",
                "Deporte (Naranja)",
                "Arte (Rojo)",
                "Entretenimiento (Rosa)",
                "Geografía (Azul)",
                "Corona a elección (Morado)"
        };

        // Mostrar mensaje
        Toast.makeText(context, "¡Ha tocado el sector: " + categorias[sector] + "!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        indicatorPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(android.graphics.ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        indicatorPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return android.graphics.PixelFormat.TRANSLUCENT;
    }

    public float getRotation() {
        return currentRotation;
    }


}
