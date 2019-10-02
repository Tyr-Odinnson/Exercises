package com.example.multiplecustomview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BitmapCanvas bitmap;
    CircleCanvas circleCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bitmap = findViewById(R.id.topview);
        circleCanvas = findViewById(R.id.bodyview);

        bitmap = new BitmapCanvas(this);
        circleCanvas = new CircleCanvas(this);
    }
}
