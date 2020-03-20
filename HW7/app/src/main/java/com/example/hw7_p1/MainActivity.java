package com.example.hw7_p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String phoneKey = "phoneyKey"; // key values for shared preferences
    final String textKey = "textKey";
    final String preferences = "preferences";

    SharedPreferences sharedPreferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext(); // context for Toasts
        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE); // SharedPreferences object to save information


    }

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
        // #TODO: phone call
    }

    public void textMessage(View view) {
        // #TODO: text message
    }
}
