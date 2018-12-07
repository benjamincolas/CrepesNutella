package com.nuitdelinfo.crepesnutella.crepesnutella;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class machine extends AppCompatActivity {

    private final int code_fenetre=20;
    private ConstraintLayout layout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);


        layout = (ConstraintLayout)this.findViewById(R.id.layout);
        layout.setOnTouchListener(new OnSwipeTouchListener(machine.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                Intent unIntent = new Intent(machine.this,MainActivity.class);
                machine.this.startActivityForResult(unIntent, code_fenetre);
            }
            public void onSwipeLeft() {

            }
            public void onSwipeBottom() {

            }

        });
    }
}
