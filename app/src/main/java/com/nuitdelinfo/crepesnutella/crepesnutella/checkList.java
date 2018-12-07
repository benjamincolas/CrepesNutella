package com.nuitdelinfo.crepesnutella.crepesnutella;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class checkList extends AppCompatActivity {

    private final int code_fenetre=20;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);


        layout = (ConstraintLayout)this.findViewById(R.id.layout);
        layout.setOnTouchListener(new OnSwipeTouchListener(checkList.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {

            }
            public void onSwipeLeft() {
                Intent unIntent = new Intent(checkList.this,MainActivity.class);
                checkList.this.startActivityForResult(unIntent, code_fenetre);
            }
            public void onSwipeBottom() {

            }

        });
    }
}
