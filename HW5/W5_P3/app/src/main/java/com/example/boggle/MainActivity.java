package com.example.boggle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, BottomFragment.OnFragmentInteractionListener  {



    @Override
    public void onFragmentInteraction(Uri uri) {

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
