package com.example.boggle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.boggle.fragments.BottomFragment;
import com.example.boggle.fragments.TopFragment;
import com.example.boggle.models.Dictionary;

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

    }
}
