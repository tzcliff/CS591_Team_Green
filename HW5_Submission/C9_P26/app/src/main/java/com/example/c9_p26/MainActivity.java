package com.example.c9_p26;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BottomFragment.OnFragmentInteractionListener, TopFragment.OnFragmentInteractionListener {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (game == null) {
            game = new Game();
        }
    }

    public void sendInput(Integer input) {
        TopFragment topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        Boolean res = game.compare(input);
        topFragment.setText(res);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
