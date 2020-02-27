package com.example.boggle.models;

import android.util.Log;

import com.example.boggle.MainActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Dictionary {
    private final InputStream dictionaryInput;

    public Dictionary(InputStream dictionaryInput) {
        this.dictionaryInput = dictionaryInput;
    }

    public Boolean validWord(String word){
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(dictionaryInput));
            String line;
            while ((line = reader.readLine()) != null) {
                Log.i(MainActivity.TAG,line);
                if(line.equals(word)){
                    return true;
                }
            }
            return false;
        } catch(Exception e) {
            Log.d(MainActivity.TAG, "Dictionary Not Found!!!");
            return false;
        }
    }
}
