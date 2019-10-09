package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class ScreenCollision extends View {


    public ScreenCollision(Context context) {
        super(context);
    }
    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        Bitmap Ball = BitmapFactory.decodeResource(getResources(),R.drawable.spikeball);
        canvas.drawBitmap(Ball, 150, 200, null);
    }
}
