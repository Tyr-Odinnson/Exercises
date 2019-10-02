package com.example.multiplecustomview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class BitmapCanvas extends View {
    Paint textpaint;
    float x = this.getWidth(), y = this.getHeight() / 2, mx = 10, my = 10, cw, cy;
    Canvas canvas;
    Thread t;
    int steps = 30;
    int interval = 1000;
    float imgw, imgh;
    int score = 0;
    CircleCanvas circleCanvas;

    public BitmapCanvas(Context context) {
        super(context);
        init(null);
    }

    public BitmapCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BitmapCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BitmapCanvas(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init (@Nullable AttributeSet attrs)
    {
        textpaint = new Paint();
        circleCanvas = new CircleCanvas(getContext());
        score = circleCanvas.scorecal();
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect)
    {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cw = canvas.getWidth();
        cy = canvas.getHeight();

        x = x + mx;
        y = y + my;
        int duration = interval/steps;
        textpaint.setTextSize(40);
        Bitmap cloud = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
        Bitmap cloudscale = Bitmap.createScaledBitmap(cloud, 200, 200, true);

        imgh = cloudscale.getHeight();
        imgw = cloudscale.getWidth();

        canvas.drawBitmap(cloudscale, x, y, null);
        canvas.drawText("Score" + score, 50, 100, textpaint);

        try {
            Thread.sleep(duration);
            cloud_move();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();
    }

    public void cloud_move() {
        if (x <= 0)
        {
            mx = 10;
        }
        if (x >= cw - imgw)
        {
            mx = -10;
        }
        if (y <= 0)
        {
            my = 10;
        }
        if (y >= cy - imgh)
        {
            my = -10;
        }
    }
}
