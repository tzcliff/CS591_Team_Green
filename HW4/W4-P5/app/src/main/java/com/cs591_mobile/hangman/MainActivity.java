package com.cs591_mobile.hangman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.cs591_mobile.hangman.models.Game;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Game game;
    String guessWord = "";
    ImageView image;
    TextView textViewGoal;
    int currentImage;
    int lives = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewGoal = findViewById(R.id.txtGoal);
        textViewGoal.setGravity(Gravity.CENTER);

        if (savedInstanceState == null) {
            currentImage = 0;
            image = (ImageView) findViewById(R.id.imgHang);
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
            lives--;
            updateImage(image);
        }
        GameResult gameResult = checkStatus();
        if(gameResult == GameResult.LOSE){

            Toast toast = Toast.makeText(MainActivity.this, "You have got " + game.getScore() + " out of " + game.calculateTotalPoints() , Toast.LENGTH_LONG);
            toast.show();
            showDialog("You lose!!");
        }
        else if(gameResult == GameResult.WIN){
            Toast toast = Toast.makeText(MainActivity.this, "You have got " + game.getScore() + " out of " + game.calculateTotalPoints() , Toast.LENGTH_LONG);
            toast.show();
            showDialog("You win!!");

        }

    }
    public GameResult checkStatus(){
        if(lives <= 0){
            return GameResult.LOSE;
        }
        else{
            if(guessWord.equals(game.getWord())){
                return GameResult.WIN;
            }
            else{
                return GameResult.PLAYING;
            }
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

    public void updateImage(ImageView image) {
        currentImage++;
        switch (currentImage) {
            case 1:
                Log.i("Kobe", "image 1");
                image.setImageResource(R.drawable.hangman1);
                break;
            case 2:
                image.setImageResource(R.drawable.hangman2);
                break;
            case 3:
                image.setImageResource(R.drawable.hangman3);
                break;
            case 4:
                image.setImageResource(R.drawable.hangman4);
                break;
            case 5:
                image.setImageResource(R.drawable.hangman5);
                break;
            case 6:
                image.setImageResource(R.drawable.hangman6);
                // call game over popup because you have reached the limit of incorrect answers
                break;
            default:
                image.setImageResource(R.drawable.hangman0);
                break;
        }
    }

    public void newGame() {
        currentImage = 0; // reset to the original hangman image
        image.setImageResource(R.drawable.hangman0);
        lives = 6;
        game = new Game();
        int wordLength = game.getWord().length();
        textViewGoal = findViewById(R.id.txtGoal);
        guessWord ="";
        for(int i = 0; i < wordLength; i++){
            guessWord += "_";
        }
        updateTextField(textViewGoal);
        Log.i("Kobe", game.getWord());
        textViewGoal.setGravity(Gravity.CENTER);

    }

    public void showDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(msg)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        newGame();
                    }
                });

        // Create the AlertDialog object and return it
        Dialog dialog = builder.create();
        dialog.show();
    }


}
