package com.example.boggle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, BottomFragment.OnFragmentInteractionListener  {

    TextView instructionsLabel; // maybe we should hide the instructions after a submit? be careful this doesn't alter the linear layout
    TextView scoreLabel; // this label should display current score
    TextView enteredLabel; // this label displays the letters that have been entered so far ex: P O S E

    Button clearButton;
    Button submitButton;
    Button newGameButton;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    Button button1; // represents the first button in the grid of letters (top left)
    Button button2; // represents the second button in the grid of letters
    Button button3; // etc...
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16; // represents the last button in the grid of letters (bottom right)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instructionsLabel = findViewById(R.id.instructionsLabel);
        scoreLabel = findViewById(R.id.scoreLabel);
        enteredLabel = findViewById(R.id.enteredLabel);

        clearButton = findViewById(R.id.clearButton);
        submitButton = findViewById(R.id.submitButton);
        newGameButton = findViewById(R.id.newGameButton);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);


    }
}
