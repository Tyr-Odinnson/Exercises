package com.example.sprites;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.HashMap;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public GameView(Context context) {
        super(context);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tilesetformattedupdate1);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);

        paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);
    }

    private Bitmap bitmap;
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    private final int scale = 5;
    private final HashMap<String, Rect> sprites = new HashMap<String, Rect>() {{
        put("Carpet A", new Rect(0, 3, 48, 28));
        put("Carpet B", new Rect(48, 1, 48, 32));
        put("Floor", new Rect(96, 1, 16, 16));
    }};


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        draw(surfaceHolder.lockCanvas());
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    private Rect setSelectedSprite(String _spriteName) {
        Rect selectedSprite = sprites.get(_spriteName);
        selectedSprite.right += selectedSprite.left;
        selectedSprite.bottom += selectedSprite.top;

        return selectedSprite;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.DKGRAY);

        drawSpritesFromCentre(canvas, "Floor", 0, 0, 10, 10);
        drawSpritesFromCentre(canvas, "Carpet A", 0, 0, 1, 1);
        drawSpritesFromCentre(canvas, "Carpet B", 200, 200, 1, 1);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void drawSpritesInArea(Canvas _canvas, String _sprite, int _originX, int _originY, int _repeatsX, int _repeatsY) {
        Rect selectedSprite = setSelectedSprite(_sprite);

        for (int y = 0; y < _repeatsY; y++) {
            for (int x = 0; x < _repeatsX; x++) {
                int left = _originX + ((selectedSprite.width() * scale) * x);
                int right = left + (selectedSprite.width() * scale);
                int top = _originY + ((selectedSprite.height() * scale) * y);
                int bottom = top + (selectedSprite.height() * scale);

                Rect destinationArea = new Rect(left, top, right, bottom);
                _canvas.drawBitmap(bitmap, selectedSprite, destinationArea, null);
            }
        }
    }

    private void drawSpritesFromCentre(Canvas _canvas, String _sprite, int _originX, int _originY, int _repeatsX, int _repeatsY) {
        Rect selectedSprite = setSelectedSprite(_sprite);

        for (int y = 0; y < _repeatsY; y++) {
            for (int x = 0; x < _repeatsX; x++) {
                int left = ((getWidth() / 2) + _originX - ((scale * selectedSprite.width()) * (_repeatsX / 2))) + ((selectedSprite.width() * scale) * x) - (_repeatsX % 2 == 1 ? ((selectedSprite.width() * scale) / 2) : 0);
                int right = left + (selectedSprite.width() * scale);
                int top = ((getHeight() / 2) + _originY - ((scale * selectedSprite.height()) * (_repeatsY / 2))) + ((selectedSprite.height() * scale) * y) - (_repeatsY % 2 == 1 ? ((selectedSprite.height() * scale) / 2) : 0);
                int bottom = top + (selectedSprite.height() * scale);

                Rect destinationArea = new Rect(left, top, right, bottom);
                _canvas.drawBitmap(bitmap, selectedSprite, destinationArea, null);
            }
        }
    }
}
