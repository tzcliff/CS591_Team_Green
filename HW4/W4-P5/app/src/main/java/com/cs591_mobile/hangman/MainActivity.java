package com.cs591_mobile.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import com.cs591_mobile.hangman.models.Game;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Game game;
    String guessWord = "";

    ImageView image;

    TextView textViewGoal;

    int currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentImage = 0;
        image = (ImageView) findViewById(R.id.imgHang);

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
            updateImage(image);
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
                break;
            default:
                image.setImageResource(R.drawable.hangman6);
                break;
        }
    }

}
