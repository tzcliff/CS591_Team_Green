package com.example.boggle;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.boggle.fragments.BottomFragment;
import com.example.boggle.fragments.TopFragment;
import com.example.boggle.models.Dictionary;

import java.util.HashMap;
import java.util.Random;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, BottomFragment.OnFragmentInteractionListener  {

    public static final String TAG = "TAG";
    public Dictionary dictionary;

    private HashMap<String, Boolean> hasBeenSubmitted = new HashMap<String, Boolean>();

    private Context context;

    private TopFragment topFragment;
    private BottomFragment bottomFragment;



    private TextView instructionsLabel; // maybe we should hide the instructions after a submit? be careful this doesn't alter the linear layout




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
        topFragment = (TopFragment) getFragmentManager().findFragmentById(R.id.fragment);
        bottomFragment = (BottomFragment)getFragmentManager().findFragmentById(R.id.fragment2);


        context = getApplicationContext();


    }

    @Override
    public void newGame() {
        topFragment.newGameTop();
        bottomFragment.score = 0;
        bottomFragment.scoreLabel.setText("Score: 0");
    }

    public int numVowels(String str) { // calculates and returns the number of vowels in a given string
        int total = 0;
        for (int i=0; i <str.length(); i++) {
            if((str.charAt(i) == 'A') ||
                    (str.charAt(i) == 'E')  ||
                    (str.charAt(i) == 'I') ||
                    (str.charAt(i) == 'O') ||
                    (str.charAt(i) == 'U')) {
                total++;
            }
        }

        return total;
    }


    public void submit() {
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        CharSequence text = "";

        String enteredText = topFragment.enteredLabel.getText().toString().replace(" ", ""); // the characters that have been entered
        if (enteredText.length() < 4 ) {
            text = "You cannot submit a word less than 4 characters in length";
            toast = Toast.makeText(this, text, duration);
            toast.show();
            bottomFragment.score -= 10;
        }

        else if (numVowels(enteredText) < 2) { // All words must utilize a minimum of two vowels.
            text = "You cannot submit a word with less than 2 vowels";
            toast = Toast.makeText(context, text, duration);
            toast.show();
            bottomFragment.score -= 10;
        }

        else if (hasBeenSubmitted.containsKey(enteredText)) {
            //  You cannot generate the same word more than once, even if it’s from different letters.
            text = "This word has already been submitted";
            toast = Toast.makeText(context, text, duration);
            toast.show();
            bottomFragment.score -= 10;
        }

        else {
            if (dictionary.validWord(enteredText)) {
                int tmp = numVowels(enteredText) * 4 + enteredText.length();
                if (hasSpecCharacter(enteredText)) tmp *= 2;
                text = "That’s correct, +" + tmp;
                toast = Toast.makeText(context, text, duration);
                toast.show();
                bottomFragment.score += tmp;
            }
            else {
                text = "That’s incorrect, -10";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                bottomFragment.score -= 10;
            }


            //topFragment.generateLetters(topFragment.getView());
            //topFragment.defaultButtonBackground();
            hasBeenSubmitted.put(enteredText, true);
        }
        topFragment.clearBoard();
        bottomFragment.scoreLabel.setText("Score: " + bottomFragment.score);



    }



    public Boolean hasSpecCharacter(String str) {
        for (int i=0; i <str.length(); i++) {
            if((str.charAt(i) == 'S') ||
                    (str.charAt(i) == 'Z')  ||
                    (str.charAt(i) == 'P') ||
                    (str.charAt(i) == 'X') ||
                    (str.charAt(i) == 'Q')) {
                return true;
            }
        }
        return false;
    }



}
