package com.example.barcodescanner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Vibrator;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

import static com.example.barcodescanner.SecondActivity.AUDIENCE_NUMBER;
import static com.example.barcodescanner.SecondActivity.LESSON_NAME;
import static com.example.barcodescanner.SecondActivity.TEACHER_SURNAME;


public class MainActivity extends AppCompatActivity {

    public static int REQUEST_CODE = 1561;

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    BottomNavigationItemView button_home;
    private Intent toResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toResponse = new Intent(MainActivity.this, ScrollingActivity.class);
        toResponse.putExtra(TEACHER_SURNAME, "");
        toResponse.putExtra(LESSON_NAME, "");
        toResponse.putExtra(AUDIENCE_NUMBER, "");
        toResponse.putExtra("dayOfTheWeek", "");
        String daysArray[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfTheWeek = daysArray[day];
        surfaceView = (SurfaceView) findViewById(R.id.camerapreview);
        textView = (TextView) findViewById(R.id.textView);
        button_home = (BottomNavigationItemView) findViewById(R.id.navigation_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector).build();
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
//                == PackageManager.PERMISSION_DENIED)

//        verifyPermissions();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                String[] permissions = {Manifest.permission.CAMERA};
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CODE);
//                    return;

                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CODE);
                    return;
                }


                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if (qrCodes.size() != 0) {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {


//                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//                            vibrator.vibrate(100);
//                            Calendar calendar = Calendar.getInstance();
//                            String format = DateFormat.getDateInstance(DateFormat.DAY_OF_WEEK_FIELD).format(calendar.getTime());
                            String valueFromQR = qrCodes.valueAt(0).displayValue;
//                            textView.setText(string);

                            toResponse.putExtra("dayOfTheWeek", dayOfTheWeek);
                            toResponse.putExtra(AUDIENCE_NUMBER, valueFromQR);
                            startActivity(toResponse);


                        }
                    });
                }
            }
        });
    }

//    private void verifyPermissions() {
//        String[] permissions = {Manifest.permission.CAMERA};
//
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                permissions[0]) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CODE);
//        }
//    }

}
