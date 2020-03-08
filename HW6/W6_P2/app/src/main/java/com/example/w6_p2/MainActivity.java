package com.example.w6_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    TextView caption;
    ImageView image;
    Context context;

    enum ImageType { // custom enum to check what the image currently is
        BEAR,
        GIRAFFE
    }

    ImageType currentImage; // easy way to check what the image currently is

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("KOBE", "created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        caption = findViewById(R.id.caption);
        image = findViewById(R.id.image);
        context = getApplicationContext();

        retrieveSharedPreferenceInfo();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("KOBE", "destroyed");
        saveSharedPreferenceInfo();


    }

    private void saveSharedPreferenceInfo() {
        //1. Refer to the SharedPreference Object.
        SharedPreferences simpleAppInfo = getSharedPreferences("MainActivity", Context.MODE_PRIVATE);  //Private means no other Apps can access this.

        //2. Create an Shared Preferences Editor for Editing Shared Preferences.
        //Note, not a real editor, just an object that allows editing...

        SharedPreferences.Editor editor = simpleAppInfo.edit();

        //3. Store what's important!  Key Value Pair, what else is new...
        Log.e("KOBE", caption.getText().toString());
        editor.putString("caption", caption.getText().toString());
        if (currentImage == ImageType.BEAR) { // check what type of image we're currently using
            editor.putString("image", "bear");
        }
        else {
            editor.putString("image", "giraffe");
        }

        //4. Save your information.
        editor.apply();
    }

    private void retrieveSharedPreferenceInfo() {
        try { // try catch because if the app has never been opened, these preferences won't exist yet (this is the answer to question 2d on the worksheet)
            //1. Refer to the SharedPreference Object.
            SharedPreferences simpleAppInfo = getSharedPreferences("MainActivity", Context.MODE_PRIVATE);

            // Create Strings and set them equal to the corresponding values for each key
            String captionString = simpleAppInfo.getString("caption", "<missing>"); //The second param is the default value, eg, if the value doesn't exist.
            String imageString = simpleAppInfo.getString("image", "<missing>");

            //Retrieving data from shared preferences hashmap.
            caption.setText(captionString);

            if (imageString.equals("bear")) { // if we stored a bear
                currentImage = ImageType.BEAR; // set the image to be the bear
                image.setImageResource(R.drawable.bear); // keep track of this with our variable
            }

            else if (imageString.equals("giraffe")) {
                currentImage = ImageType.GIRAFFE;
                image.setImageResource(R.drawable.giraffe);
            }

            else {
                // No value was stored so the default of <missing> occurs and we don't have an iamge to show for this
            }
        }
        catch(Exception ex) {
            System.out.println("Exception: " + ex);
        }
    }


    public void firstButtonClick(View v) {
        caption.setText("Bear");
        image.setImageResource(R.drawable.bear);
        currentImage = ImageType.BEAR;
        saveSharedPreferenceInfo(); // also saving here because sometimes onDestroy isn't called, for instance swiping the app away in the recent apps Android screen

    }

    public void secondButtonClick(View v) {
        caption.setText("Giraffe");
        image.setImageResource(R.drawable.giraffe);
        currentImage = ImageType.GIRAFFE;
        saveSharedPreferenceInfo(); // also saving here because sometimes onDestroy isn't called, for instance swiping the app away in the recent apps Android screen

    }


}
