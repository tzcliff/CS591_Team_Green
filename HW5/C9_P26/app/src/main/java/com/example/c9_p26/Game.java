package com.example.c9_p26;

import java.util.Random;

public class Game {
    private Random random;
    private int rNumber;

    public Game() {
        random = new Random();
        rNumber = 1 + random.nextInt(5);
    }

    public boolean compare(Integer input) {
        return rNumber == input;
    }
}
