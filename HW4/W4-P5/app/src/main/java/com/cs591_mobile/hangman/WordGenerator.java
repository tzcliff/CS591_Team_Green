package com.cs591_mobile.hangman;

import java.util.Random;

public class WordGenerator {
    private String[] words = {"Lamborghini", "Donut", "Horse", "Apartment", "Buoy", "Helicopter", "Blueberry"};
    private String[] hints = {"A brand of car", "A sweet food", "A farm animal", "Somewhere you live", "It floats", "It flies", "A fruit"};


    public static void main(String argsp[]) {
        WordGenerator a = new WordGenerator();
        int num = a.chooseWord();
        System.out.println("chose" + num + " which corresponds to: " + a.words[num] + " which has a hint of: " + a.hints[num]);
    }


    public int chooseWord() { // this method randomly returns the index of the word and it's corresponding hint
        Random rand = new Random();
        return rand.nextInt(words.length - 1); // choose a random index of the words array
    }

}


