package com.example.textcollision;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class TextCollision extends View {

    Paint textPaint = new Paint();
    Random r;
    float textx, texty;
    float textwidth;
    float textheight;
    String[] list = {"a", "i", "u", "e", "o"};
    Bitmap fly;
    float imgw, imgh;


    public TextCollision(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        r = new Random();
        fly = BitmapFactory.decodeResource(getResources(),R.drawable.fly);
        Bitmap flyScale = Bitmap.createScaledBitmap(fly, 200, 200, true);
        imgh = flyScale.getHeight();
        imgw = flyScale.getWidth();
        canvas.drawBitmap(flyScale, canvas.getWidth()-flyScale.getWidth(), canvas.getHeight()-flyScale.getHeight(),  null);
        textPaint.setColor(Color.rgb(170, 0, 170));
        textPaint.setTextSize(80);
        imgh = flyScale.getHeight();
        imgw = flyScale.getWidth();





    for (int i = 0; i < list.length; i++) {
        textx = r.nextInt(canvas.getWidth());
        texty = r.nextInt(canvas.getHeight());
        canvas.drawText(list[i], textx, texty, textPaint);

        textwidth = textPaint.measureText(list[i]);

        if (textx + textwidth >= canvas.getWidth())
            textx = textx - textwidth;

        if (texty + textheight >= canvas.getHeight())
            texty = texty - textheight;
        if (textx <= 0)
            textx = textwidth + textx;
        if (texty <= 0)
            texty = textheight + texty;
        }

    }
}
