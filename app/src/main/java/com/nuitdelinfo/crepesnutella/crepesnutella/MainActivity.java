package com.nuitdelinfo.crepesnutella.crepesnutella;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn_photo;
    private ImageButton btn_torche;
    private ImageButton btn_sos;
    private int click;
    private final int code_fenetre=20;
    private ConstraintLayout layout;

    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        final boolean hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        //boolean isEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        btn_photo = (ImageButton)this.findViewById(R.id.btnPhoto);
        btn_photo.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

        btn_torche = (ImageButton)this.findViewById(R.id.btnTorche);
        btn_torche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);

                if (hasCameraFlash) {
                    if (flashLightStatus)
                        flashLightOff();
                    else
                        flashLightOn();
                } else {
                    Toast.makeText(MainActivity.this, "No flash available on your device",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_sos = (ImageButton)this.findViewById(R.id.btnsos);
        btn_sos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                click++;
                if(click == 2){
                    //ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE},);
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "911"));
                    startActivity(intent);
                    click = 0;
                }
            }
        });
        layout = (ConstraintLayout)this.findViewById(R.id.layout);
        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Intent unIntent = new Intent(MainActivity.this,meteoActivity.class);
                MainActivity.this.startActivityForResult(unIntent, code_fenetre);
            }
            public void onSwipeRight() {
                Intent unIntent = new Intent(MainActivity.this,checkList.class);
                MainActivity.this.startActivityForResult(unIntent, code_fenetre);
            }
            public void onSwipeLeft() {
                Intent unIntent = new Intent(MainActivity.this,machine.class);
                MainActivity.this.startActivityForResult(unIntent, code_fenetre);
            }
            public void onSwipeBottom() {
                Intent unIntent = new Intent(MainActivity.this,map.class);
                MainActivity.this.startActivityForResult(unIntent, code_fenetre);
            }

        });
    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            flashLightStatus = true;
        } catch (CameraAccessException e) {
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            flashLightStatus = false;
        } catch (CameraAccessException e) {
        }
    }
}