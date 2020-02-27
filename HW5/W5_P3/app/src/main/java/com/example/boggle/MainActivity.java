package com.example.boggle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.boggle.fragments.BottomFragment;
import com.example.boggle.fragments.TopFragment;
import com.example.boggle.models.Dictionary;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, BottomFragment.OnFragmentInteractionListener  {

    public static final String TAG = "TAG";
    public Dictionary dictionary;


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize dictionary
        try {
            dictionary = new Dictionary(getAssets().open("dictionary.txt"));
        }
        catch (Exception e){
            Log.i(TAG,"Dictionary doesn't exist!!");
        }
        setContentView(R.layout.activity_main);
        generateLetters();

    }

    public String generateRandLetter (){
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        String s = Character.toString(c);
        return s;
    }

    public void generateLetters(){
        Button b1 = (Button)findViewById(R.id.button1);
        b1.setText(generateRandLetter());
        Button b2 = (Button)findViewById(R.id.button2);
        b2.setText(generateRandLetter());
        Button b3 = (Button)findViewById(R.id.button3);
        b3.setText(generateRandLetter());
        Button b4 = (Button)findViewById(R.id.button4);
        b4.setText(generateRandLetter());
        Button b5 = (Button)findViewById(R.id.button5);
        b5.setText(generateRandLetter());
        Button b6 = (Button)findViewById(R.id.button6);
        b6.setText(generateRandLetter());
        Button b7 = (Button)findViewById(R.id.button7);
        b7.setText(generateRandLetter());
        Button b8 = (Button)findViewById(R.id.button8);
        b8.setText(generateRandLetter());
        Button b9 = (Button)findViewById(R.id.button9);
        b9.setText(generateRandLetter());
        Button b10 = (Button)findViewById(R.id.button10);
        b10.setText(generateRandLetter());
        Button b11 = (Button)findViewById(R.id.button11);
        b11.setText(generateRandLetter());
        Button b12 = (Button)findViewById(R.id.button12);
        b12.setText(generateRandLetter());
        Button b13 = (Button)findViewById(R.id.button13);
        b13.setText(generateRandLetter());
        Button b14 = (Button)findViewById(R.id.button14);
        b14.setText(generateRandLetter());
        Button b15 = (Button)findViewById(R.id.button15);
        b15.setText(generateRandLetter());
        Button b16 = (Button)findViewById(R.id.button16);
        b16.setText(generateRandLetter());
    }
}
