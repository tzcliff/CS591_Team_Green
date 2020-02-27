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
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, BottomFragment.OnFragmentInteractionListener, View.OnClickListener  {

    public static final String TAG = "TAG";
    public Dictionary dictionary;

    TextView scoreLabel; // this label should display current score

    Button newGameButton;

    private TextView enteredLabel; // this label displays the letters that have been entered so far ex: P O S E
    private TextView instructionsLabel; // maybe we should hide the instructions after a submit? be careful this doesn't alter the linear layout


    private Button b1; // represents the first button in the grid of letters (top left)
    private Button b2; // represents the second button in the grid of letters
    private Button b3; // etc...
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;
    private Button b11;
    private Button b12;
    private Button b13;
    private Button b14;
    private Button b15;
    private Button b16; // represents the last button in the grid of letters (bottom right)

    private Button clearButton;
    private Button submitButton;




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
            Log.e(TAG,"Dictionary doesn't exist!!");
        }
        setContentView(R.layout.activity_main);
        generateLetters();

        scoreLabel = findViewById(R.id.scoreLabel);
        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(this);

    }

    public String generateRandLetter (){
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        String s = Character.toString(c);
        return s;
    }

    public void generateLetters(){


        b1 = (Button)findViewById(R.id.button1);
        b1.setText(generateRandLetter());
        b1.setOnClickListener(this);
        b2 = (Button)findViewById(R.id.button2);
        b2.setText(generateRandLetter());
        b2.setOnClickListener(this);
        b3 = (Button)findViewById(R.id.button3);
        b3.setText(generateRandLetter());
        b3.setOnClickListener(this);
        b4 = (Button)findViewById(R.id.button4);
        b4.setText(generateRandLetter());
        b4.setOnClickListener(this);
        b5 = (Button)findViewById(R.id.button5);
        b5.setText(generateRandLetter());
        b5.setOnClickListener(this);
        b6 = (Button)findViewById(R.id.button6);
        b6.setText(generateRandLetter());
        b6.setOnClickListener(this);
        b7 = (Button)findViewById(R.id.button7);
        b7.setText(generateRandLetter());
        b7.setOnClickListener(this);
        b8 = (Button)findViewById(R.id.button8);
        b8.setText(generateRandLetter());
        b8.setOnClickListener(this);
        b9 = (Button)findViewById(R.id.button9);
        b9.setText(generateRandLetter());
        b9.setOnClickListener(this);
        b10 = (Button)findViewById(R.id.button10);
        b10.setText(generateRandLetter());
        b10.setOnClickListener(this);
        b11 = (Button)findViewById(R.id.button11);
        b11.setText(generateRandLetter());
        b11.setOnClickListener(this);
        b12 = (Button)findViewById(R.id.button12);
        b12.setText(generateRandLetter());
        b12.setOnClickListener(this);
        b13 = (Button)findViewById(R.id.button13);
        b13.setText(generateRandLetter());
        b13.setOnClickListener(this);
        b14 = (Button)findViewById(R.id.button14);
        b14.setText(generateRandLetter());
        b14.setOnClickListener(this);
        b15 = (Button)findViewById(R.id.button15);
        b15.setText(generateRandLetter());
        b15.setOnClickListener(this);
        b16 = (Button)findViewById(R.id.button16);
        b16.setText(generateRandLetter());
        b16.setOnClickListener(this);
    }

    public void onClick(View v) {
        // required method stub for View.OnClickListener
        Log.e(TAG, "inside onClick function in MainActivity");
        String message = "";

        switch (v.getId()) { // switch statement determines which of the letter buttons have been pressed

            case R.id.clearButton:
                // #TODO do clear stuff here
                Log.e(TAG, "clear button pressed");
                break;

            case R.id.submitButton:
                // #TODO handle submit stuff here
                Log.e(TAG, "submit button pressed");
                break;

            case R.id.newGameButton:
                // #TODO do new game stuff here
                Log.e(TAG, "new game");
                break;

            case R.id.button1:
                message += b1.getText().toString();
                break;

            case R.id.button2:
                message += b2.getText().toString();
                break;

            case R.id.button3:
                message += b3.getText().toString();
                break;

            case R.id.button4:
                message += b4.getText().toString();
                break;

            case R.id.button5:
                message += b5.getText().toString();
                break;

            case R.id.button6:
                message += b6.getText().toString();
                break;

            case R.id.button7:
                message += b7.getText().toString();
                break;

            case R.id.button8:
                message += b8.getText().toString();
                break;

            case R.id.button9:
                message += b9.getText().toString();
                break;

            case R.id.button10:
                message += b10.getText().toString();
                break;

            case R.id.button11:
                message += b11.getText().toString();
                break;

            case R.id.button12:
                message += b12.getText().toString();
                break;

            case R.id.button13:
                message += b13.getText().toString();
                break;

            case R.id.button14:
                message += b14.getText().toString();
                break;

            case R.id.button15:
                message += b15.getText().toString();
                break;

            case R.id.button16:
                message += b16.getText().toString();
                break;

            default:
                Log.e(TAG, "unknown click");
                break;
        }

        message += " button has been pressed";
        Log.e(TAG, message);

    }
}
