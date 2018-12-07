package com.nuitdelinfo.crepesnutella.crepesnutella;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class map extends AppCompatActivity {

    private ImageView map;
    private final int code_fenetre=20;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        map = (ImageView) this.findViewById(R.id.imageView);
        map.setOnTouchListener(new OnSwipeTouchListener(map.this) {
            public void onSwipeTop() {
                Intent unIntent = new Intent(map.this,MainActivity.class);
                map.this.startActivityForResult(unIntent, code_fenetre);
            }
            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
            }
            public void onSwipeBottom() {
            }

        });
    }

}
