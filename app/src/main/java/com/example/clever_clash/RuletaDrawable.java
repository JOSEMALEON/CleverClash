package com.example.clever_clash;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class RuletaDrawable extends Drawable {

    private Paint paint;
    private int[] colors = {
            Color.parseColor("#4CAF50"), // Ciencia (Verde)
            Color.parseColor("#FFEB3B"), // Historia (Amarillo)
            Color.parseColor("#FF9800"), // Deporte (Naranja)
            Color.parseColor("#F44336"), // Arte (Rojo)
            Color.parseColor("#E91E63"), // Entretenimiento (Rosa)
            Color.parseColor("#2196F3"), // Geografía (Azul)
            Color.parseColor("#9C27B0")  // Corona a elección (Morado)
    };

    @Override
    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int radius = Math.min(width, height) / 2; // Radio de la ruleta

        float startAngle = 0; // Ángulo inicial
        float sweepAngle = 360f / colors.length; // Ángulo para cada sector

        RectF rect = new RectF(0, 0, width, height); // El área en la que se dibujará el círculo

        // Dibuja cada sector de la ruleta
        for (int i = 0; i < colors.length; i++) {
            paint.setColor(colors[i]); // Establece el color para cada sector
            canvas.drawArc(rect, startAngle, sweepAngle, true, paint);
            startAngle += sweepAngle; // Incrementa el ángulo para el siguiente sector
        }
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(android.graphics.ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return android.graphics.PixelFormat.TRANSLUCENT;
    }

    public RuletaDrawable() {
        paint = new Paint();
        paint.setAntiAlias(true); // Suaviza los bordes
        paint.setStyle(Paint.Style.FILL); // Rellena los sectores
    }
}
