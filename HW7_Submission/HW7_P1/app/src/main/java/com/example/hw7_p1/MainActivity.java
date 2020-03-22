package com.example.hw7_p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // value used to determine whether user shook the device "significantly"
    private static int SIGNIFICANT_SHAKE = 1000;   //tweak this as necessary

    final String phoneKey = "phoneyKey"; // key values for shared preferences
    final String textKey = "textKey";
    final String preferences = "preferences";

    ImageButton phone;
    ImageButton text;

    SharedPreferences sharedPreferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext(); // context for Toasts
        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE); // SharedPreferences object to save information

        enableAccelerometerListening(); // strong shake will call and text

        phone = findViewById(R.id.phoneButton);
        text = findViewById(R.id.textButton);


    }

    private void enableAccelerometerListening() {

        // This is how we get the reference to the device's SensorManager.
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);    //The last parm specifies the type of Sensor we want to monitor


        //Now that we have a Sensor Handle, let's start "listening" for movement (accelerometer).
        //3 parms, The Listener, Sensor Type (accelerometer), and Sampling Frequency.
        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);   //don't set this too high, otw you will kill user's battery.

    }

    @Override
    protected void onStop() {
        disableAccelerometerListening(); // always disable when done using
        super.onStop();
    }

    // disable listening for accelerometer events
    private void disableAccelerometerListening() {

//Disabling Sensor Event Listener is two step process.
        //1. Retrieve SensorManager Reference from the activity.
        //2. call unregisterListener to stop listening for sensor events
        //THis will prevent interruptions of other Apps and save battery.

        // get the SensorManager
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);

        // stop listening for accelerometer events
        sensorManager.unregisterListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];   //obtaining the latest sensor data.
            float y = event.values[1];   //sort of ugly, but this is how data is captured.
            float z = event.values[2];
            float acceleration = x * x + y * y + z * z;

            if (acceleration > SIGNIFICANT_SHAKE) {
                Log.e("BOSTON", "significantShake");
                Toast.makeText(context, "Significant shake detected, contacting emergency contacts", Toast.LENGTH_SHORT).show();
                text.performClick(); // click the button to access onClick method
                phone.performClick();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // required to implement but we do not need
        }
    };

    // 1. Show the menu items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.pref_menu, menu);
        return true;
    }

    // 2. Handle menu selections
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.prefMenu) {
            // load preference activity
            // 1. declare an intent with the current and next activity
            Intent myIntent = new Intent(MainActivity.this, PreferencesActivity.class);
            // 2. start the next activity passing in the intent we just created
            MainActivity.this.startActivity(myIntent);
            return true; // return true to tell this function that we handled the menu selection
        }
        return super.onOptionsItemSelected(item); // default fallthrough behavior
    }

    public void phoneCall(View view) {
        if (sharedPreferences.getString(phoneKey, "") == "") { // if the phone number is blank, alert the user
            Toast.makeText(context, "Please enter a phone # in the preferences", Toast.LENGTH_LONG).show();
        }
        else {
            String phoneNum = sharedPreferences.getString(phoneKey, ""); // get saved phone number
            Intent phoneCall = new Intent(Intent.ACTION_DIAL); // create intent
            phoneCall.setData(Uri.parse("tel:" + phoneNum)); // pass in the phone number to call
            startActivity(phoneCall); // start the Activity
        }
    }

    public void textMessage(View view) {
        if (sharedPreferences.getString(textKey, "") == "") {
            Toast.makeText(context, "Please enter a # to text in the preferences.", Toast.LENGTH_LONG).show();
        }
        else {
            // automatic approach to send SMS
            final String message = "**THIS IS ONLY A TEST**";
            String phoneNum = sharedPreferences.getString(textKey, ""); // get saved phone number

            try {
                SmsManager SMS = SmsManager.getDefault();
                SMS.sendTextMessage(phoneNum, null, message, null, null);
                Toast.makeText(context, "SMS sent.", Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                Toast.makeText(context, "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                ex.printStackTrace();
                System.out.println("Eception: " + ex);
            }
        }

    }
}
