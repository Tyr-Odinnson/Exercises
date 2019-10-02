package com.example.exercises;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class ImageCollision extends View {


    private int score = 0;

    Random r;
    float x, y;
    float canvasw,canvash;
    int red = 10;
    int blue = 10;
    int green = 10;
    float ballx = 200, bally = 200;
    float batx, baty;
    float batw, bath, ballw, ballh;
    Paint canvaspaint;
    Paint image2paint;
    Animation animation;
    Bitmap img1, img2, bat, ball;

    public ImageCollision(Context context) {
        super(context);
        r = new Random();
        canvaspaint = new Paint();
        image2paint = new Paint();
        img1 = BitmapFactory.decodeResource(getResources(),R.drawable.bat);
        img2 = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        batx = canvasw /2;
        baty = canvash /6;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasw = canvas.getWidth();
        canvash = canvas.getHeight();

        canvaspaint.setColor(Color.rgb(red,green,blue));
        canvas.drawColor(Color.rgb(red,green,blue));

        ball = Bitmap.createScaledBitmap(img2, 200, 200, true);
        canvas.drawBitmap(ball, ballx, bally, image2paint);

        Paint painttext = new Paint();
        painttext.setTextSize(70);
        painttext.setColor(Color.rgb(50,0,50));
        canvas.drawText("score :", 10, 50, painttext);
        canvas.drawText(Integer.toString(score), 250, 50, painttext);
        bat = Bitmap.createScaledBitmap(img1, 200, 200, true);
        canvas.drawBitmap(bat, batx, baty, null);
        batw = bat.getWidth();
        bath = bat.getHeight();
        ballh = ball.getHeight();
        ballw = ball.getWidth();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE)
        {
            batx = event.getX();
            baty = event.getY();
            cornercheck();
            visible();
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            score = collision();
        }
        if (event.getAction() == MotionEvent.ACTION_HOVER_EXIT)
        {
            visible();
        }
        invalidate();
        return true;
    }
    public int collision() {
        //x position + image x1 > image1 x position && image2 x position < image x2 position
        //if (x+image2.getWidth() > 200 && x < img2.getWidth() && y+img.getHeight() > 200 && y < img1.getHeight() )
        //if (batx+200 > 200 && batx < 400 && baty + 200 > 200 && baty < 400 )
        if (batx + ballw > ballx && batx <= batw + ballw && baty + bath > bally && baty <= bath+ballh)
        {
            score = score + 10;

            red = r.nextInt(255);
            blue = r.nextInt(255);
            green = r.nextInt(255);

            invisible();
        }
        return score;
    }
    public void newposition () {
        ballx = r.nextInt((int)canvasw);
        if (ballx <=0)
            ballx += img2.getWidth();
        if (ballx >= canvasw)
            ballx -= img2.getWidth();
        bally = r.nextInt((int)canvash) - img2.getHeight();
    }

    public void invisible()
    {
        image2paint.setAlpha(0);
    }
    public void visible ()
    {
        newposition();
        image2paint.setAlpha(255);
        img2 = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
    }
    public void cornercheck ()
    {
        if (batx >= canvasw || baty >= canvash)
            score -= 10;
    }
}
