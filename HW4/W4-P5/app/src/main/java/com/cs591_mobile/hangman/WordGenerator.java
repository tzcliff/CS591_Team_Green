package com.cs591_mobile.hangman;

import java.util.Random;

public class WordGenerator {
    public String[] words = {"TERRIER", "DONUT", "HORSE", "HOUSE", "Buoy", "HELICOPTER", "BLUEBERRY"};
    public String[] hints = {"A BU mascot", "A sweet food", "A farm animal", "Somewhere you live", "It floats", "It flies", "A fruit"};


    public static void main(String args[]) {
        WordGenerator a = new WordGenerator();
        int num = a.chooseWord();
        System.out.println("chose" + num + " which corresponds to: " + a.words[num] + " which has a hint of: " + a.hints[num]);
    }


    public int chooseWord() { // this method randomly returns the index of the word and it's corresponding hint
        Random rand = new Random();
        return rand.nextInt(words.length - 1); // choose a random index of the words array
    }



}


