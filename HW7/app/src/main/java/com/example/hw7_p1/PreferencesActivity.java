package com.example.hw7_p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;
import android.content.SharedPreferences;

public class PreferencesActivity extends AppCompatActivity {

    EditText phone; // edit text for phone number for emergency call
    EditText text; // edit text for phone number for emergency text
    Context context;
    final String phoneKey = "phoneyKey"; // key values for shared preferences
    final String textKey = "textKey";
    final String preferences = "preferences";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences); // don't forget to declare this activity in the manifest!

        phone = findViewById(R.id.phoneEdit);
        text = findViewById(R.id.textEdit);

        context = getApplicationContext(); // context for Toasts
        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE); // SharedPreferences object to save information

        try { // empty default values so that if no preferences has been saved before then the EditText stays empty; we'll use a try catch just in case though
            phone.setText(sharedPreferences.getString(phoneKey, ""));
            text.setText(sharedPreferences.getString(textKey, ""));
        }
        catch(Exception ex) {
            System.out.println("Exception: " + ex);
        }


    }

    public void save(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit(); // create an editor object
        editor.putString(phoneKey, phone.getText().toString()); // put the information, key value pairs
        editor.putString(textKey, text.getText().toString());
        editor.commit(); // always commit changes

        // Toast
        CharSequence text = "Information Saved!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }

    public void clear(View view) {
        // delete visible text
        phone.setText("");
        text.setText("");

        // delete preferences
        SharedPreferences.Editor editor = sharedPreferences.edit(); // create an editor object
        editor.remove(phoneKey); // remove by key
        editor.remove(textKey);
        editor.commit(); // always commit changes
    }

}
