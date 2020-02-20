package com.cs591_mobile.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cs591_mobile.hangman.models.Game;


public class MainActivity extends AppCompatActivity {

    Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        game = new Game();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view){
        Button button = (Button)view;
        String str = button.getText().toString();

    }


}
