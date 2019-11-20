package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class ScreenCollision extends View {

    float x = 0;
    float y = 0;
    float imgw, imgh;


    public ScreenCollision(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        Bitmap Ball = BitmapFactory.decodeResource(getResources(),R.drawable.spikeball);
        Bitmap Ballscale = Bitmap.createScaledBitmap(Ball, 200, 200, true);
        imgh = Ballscale.getHeight();
        imgw = Ballscale.getWidth();

        canvas.drawBitmap(Ballscale, x, y, null);

        if (x + Ballscale.getWidth() <= canvas.getWidth()) {
            x = x + 10;
        } else if (y + Ballscale.getHeight() <= canvas.getHeight()) {
            y = y + 10;
        } else if (x + Ballscale.getWidth() <= canvas.getWidth()) {
            x = x - 10;
        }
        invalidate();

    }
}
