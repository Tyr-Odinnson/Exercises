package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class ImageMovement extends View {

    float x = 10;
    float y = 10;
    float imagex;
    float imagey;
    float imgw, imgh;

    public ImageMovement(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        this.setSystemUiVisibility(uiOptions);

        Bitmap Spike = BitmapFactory.decodeResource(getResources(),R.drawable.spikeball);
        Bitmap Spikescale = Bitmap.createScaledBitmap(Spike, 200, 200, true);
        imgh = Spikescale.getHeight();
        imgw = Spikescale.getWidth();

        canvas.drawBitmap(Spikescale, imagex, imagey, null);

        imagex = imagex + x;
        imagey = imagey + y;

        if (imagex + imgw >= canvas.getWidth())
            x -= 10;
        if (imagey + imgh >= canvas.getHeight())
            y -= 10;
        if (imagex <= 0)
            x = 10;
        if (imagey <= 0)
            y = 10;
        invalidate();
    }

}
