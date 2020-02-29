package com.example.boggle.models;

import android.util.Log;

import com.example.boggle.MainActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<String> wordList = new ArrayList<>();

    public Dictionary(InputStream dictionaryInput) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(dictionaryInput));
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.toUpperCase());
            }
        } catch(Exception e) {
            Log.d(MainActivity.TAG, "Dictionary Not Found!!!");
        }
    }

    public Boolean validWord(String word){
        for (String key: wordList) {
            if (key.equals(word)){
                Log.i(MainActivity.TAG,key);
                return true;
            }
        }
        return false;
    }
}
