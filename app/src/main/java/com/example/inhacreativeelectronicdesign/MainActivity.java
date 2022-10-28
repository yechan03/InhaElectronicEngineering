package com.example.inhacreativeelectronicdesign;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements BluetoothGuide.OnCheckModelListener, BluetoothGuide.OnNotifyValueListener<Ev3sensor>{

    Button Camera_button;
    BluetoothGuide bluetoothGuide = new BluetoothGuide();
    Button bluetooth_button;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Camera_button = findViewById(R.id.camera_go_button);
        bluetooth_button = findViewById(R.id.bluetooth_connect);





        bluetooth_button.setOnClickListener(v -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.BLUETOOTH,
                                Manifest.permission.BLUETOOTH_SCAN,
                                Manifest.permission.BLUETOOTH_ADVERTISE,
                                Manifest.permission.BLUETOOTH_CONNECT


                        },
                        1);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.BLUETOOTH

                        },
                        1);
            }

            bluetoothGuide.setOnCheckModelListener(this).setOnNotifyValueListener(this);
            bluetoothGuide.scanDevices();


        });
        Camera_button.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), EV3CameraActivity.class);
            startActivity(intent);
        });





    }


    @Override
    public boolean isChecked(byte[] bytes) {
        return false;
    }

    @Override
    public void scannedDevice(ScanResult result) {

    }

    @Override
    public void onValue(BluetoothDevice deivce, Ev3sensor value) {

    }


    @Override
    public Ev3sensor formatter(BluetoothGattCharacteristic characteristic) {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}
