package com.cs591_mobile.hangman;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs591_mobile.hangman.models.Game;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Game game;
    String guessWord = "";

    TextView textViewGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGoal = findViewById(R.id.txtGoal);
        textViewGoal.setGravity(Gravity.CENTER);

        if (savedInstanceState == null) {
            game = new Game();
            int wordLength = game.getWord().length();
            guessWord ="";
            for(int i = 0; i < wordLength; i++){
                guessWord += "_";
            }
            updateTextField(textViewGoal);
            Log.i("Kobe", game.getWord());

        } else {
            guessWord = savedInstanceState.getString("guessWord");
            String StringGame = savedInstanceState.getString("game");
            game = new Gson().fromJson(StringGame, Game.class);
            updateTextField(textViewGoal);
            Log.i("Jiang", game.getWord());
        }



    }

    public void click(View view){
        Button button = (Button)view;
        String str = button.getText().toString();

        Log.i("T", str);
        ArrayList<Integer> indices = game.guess(str);
        if(indices.size() != 0){
            for(Integer i : indices){
                guessWord = guessWord.substring(0, i) + game.getWord().charAt(i) + guessWord.substring(i + 1);
                Log.i("Kobe", guessWord);
                updateTextField(textViewGoal);
            }
        }
        else{
            // todo next pic
        }
    }

    public void updateTextField(TextView textView){
        String temp = "";
        for(int i = 0; i < guessWord.length(); i++){
            temp += " " + guessWord.charAt(i);
        }
        Log.i("t", guessWord);
        textView.setText(temp);
        textView.setGravity(Gravity.CENTER);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("guessWord", guessWord);
        outState.putString("game", new Gson().toJson(game));

        super.onSaveInstanceState(outState);
    }
}
