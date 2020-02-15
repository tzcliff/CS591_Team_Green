package com.cs591_mobile.accelerametermeasurements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static Integer sensitivity = 15;
    private final String TAG = "Acceleration";
    private SeekBar seekBarSensitivity;
    private TextView labelSensitivity;
    private WebView webView;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            double x = Math.abs(sensorEvent.values[0]);
            double y = Math.abs(sensorEvent.values[1]);
            double z = Math.abs(sensorEvent.values[2]);


            if(x > sensitivity || y > sensitivity || z > sensitivity){

                accelerationThreshold((int)x, (int)y, (int)z);
                Log.e("Acceleration","delta x = " + x);
                Log.e("Acceleration","delta y = " + y);
                Log.e("Acceleration","delta z = " + z);
                if(x + y + z >= 2.5 * sensitivity){
                    webView.loadUrl("https://jumpingjaxfitness.files.wordpress.com/2010/07/dizziness.jpg");
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarSensitivity = findViewById(R.id.seekBarSensitivity);
        labelSensitivity = findViewById(R.id.labelSensitivity);
        webView = findViewById(R.id.webView);

        seekBarSensitivity.setMax(sensitivity);
        seekBarSensitivity.setProgress(sensitivity);
        labelSensitivity.setText(sensitivity.toString());
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MainActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl("http://developer.android.com/");



        seekBarSensitivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                sensitivity = progress;
                labelSensitivity.setText(sensitivity.toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart Triggered.");
        enableAccelerometerListening();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop Triggered.");
        disableAccelerometerListening();
        super.onStop();
    }

    private void enableAccelerometerListening() {
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);    //The last parm specifies the type of Sensor we want to monitor


        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }



    private void disableAccelerometerListening() {

        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);

        sensorManager.unregisterListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    public void accelerationThreshold(int x, int y, int z){
        String msg = "";
        if (x > y && x > z) {
            msg = "Moving Fast on X!!!";
            webView.loadUrl("https://www.ecosia.org/");
        }
        else if (y > x && y > z) {
            msg = "Moving Fast on Y!!!";
            webView.loadUrl("https://www.dogpile.com/");
        }
        else if(z > y && z > x){
            msg = "Moving Fast on Z!!!";
            webView.loadUrl("https://buzzsumo.com/");
        }

        Toast.makeText(MainActivity.this, msg,
                Toast.LENGTH_LONG).show();
    }


}
