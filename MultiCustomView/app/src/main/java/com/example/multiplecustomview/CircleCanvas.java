package com.example.multiplecustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Random;

public class CircleCanvas extends View {

    float circlex, circley, cradius;
    Paint circlepaint;
    int red, green, blue;
    Random r;
    public static int x = 0;

    public CircleCanvas(Context context) {
        super(context);
        init(null);
    }
    public CircleCanvas (Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public CircleCanvas (Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleCanvas (Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init (@Nullable AttributeSet attrs) {
        circlex = 0;
        circley = 0;
        cradius = 30;
        circlepaint = new Paint();
        r = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawColor(Color.Gray);

        for (int i = 0; i < 5; i++) {
            red = r.nextInt(255);
            green = r.nextInt(255);
            blue = r.nextInt(255);

                circlepaint.setColor(Color.rgb(red, green, blue));

            circley = r.nextInt(canvas.getHeight());
            circlex = r.nextInt(canvas.getWidth());

            if (circlex <= 0)
            {
                circlex = circlex + cradius;
            }
            if (circlex >= canvas.getWidth())
            {
                circlex = circlex - cradius;
            }
            if (circley <= 0)
            {
                circley = circley + cradius;
            }
            if (circley >= canvas.getHeight())
            {
                circley = circley - cradius;
            }

            canvas.drawCircle(circlex, circley, cradius, circlepaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            x++;
        return true;
    }
    public int scorecal() {
        return x++;
    }
}
