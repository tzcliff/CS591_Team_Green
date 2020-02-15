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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static Integer sensitivity = 80;
    private final String TAG = "Acceleration";
    private SeekBar seekBarSensitivity;
    private TextView labelSensitivity

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            double x = sensorEvent.values[0];
            double y = sensorEvent.values[1];
            double z = sensorEvent.values[2];

            double currentAcceleration = Math.sqrt((x * x + y * y + z * z));
            if(currentAcceleration > sensitivity){
                Toast.makeText(MainActivity.this, "Moving Fast!!!",
                        Toast.LENGTH_LONG).show();
                Log.e("Acceleration","delta x = " + x);
                Log.e("Acceleration","delta y = " + y);
                Log.e("Acceleration","delta z = " + z);
                Log.e("Acceleration","Acceleration = " + currentAcceleration);
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

        SeekBar seekBarSensitivity = findViewById(R.id.seekBarSensitivity);
        TextView labelSensitivity = findViewById(R.id.labelSensitivity);
        WebView webView = findViewById(R.id.webView);

        seekBarSensitivity.setMax(sensitivity);
        seekBarSensitivity.setProgress(sensitivity);
        labelSensitivity.setText(sensitivity.toString());

        webView .loadUrl("http://www.google.com");



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


}
