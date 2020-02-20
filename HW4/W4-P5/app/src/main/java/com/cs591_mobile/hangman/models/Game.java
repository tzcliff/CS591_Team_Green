package com.cs591_mobile.hangman.models;

import com.cs591_mobile.hangman.WordGenerator;

import java.util.ArrayList;

public class Game {
    private String word;
    private String hint;
    private int score;
    private final WordGenerator wordGenerator;


    public Game() {
        score = 0;
        wordGenerator = new WordGenerator();
        int index = wordGenerator.chooseWord();
        word = wordGenerator.words[index];
        hint = wordGenerator.hints[index];
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Integer> guess(String str){

        if(word.contains(str)){
            ArrayList<Integer> indices = new ArrayList<>();
            for(int i = 0; i < word.length(); i++){
                if(String.valueOf(word.charAt(i)) == str){
                    indices.add(i);
                    if(checkVowel(str)){
                        score += 5;
                    }
                    else{
                        score += 2;
                    }
                }
            }
            return indices;
        }
        else{
            return null;
        }

    }


    public boolean checkVowel(String ch){
        if(ch == "A" || ch == "E" || ch == "I" || ch == "O" || ch == "U" )
            return true;
        else
            return false;

    }

}
