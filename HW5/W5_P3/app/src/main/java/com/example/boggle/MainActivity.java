package com.example.boggle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.boggle.fragments.BottomFragment;
import com.example.boggle.fragments.TopFragment;
import com.example.boggle.models.Dictionary;

import java.util.HashMap;
import java.util.Random;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, BottomFragment.OnFragmentInteractionListener, View.OnClickListener  {

    public static final String TAG = "TAG";
    public Dictionary dictionary;
    private HashMap<Button, Boolean> hasBeenPressed = new HashMap<Button, Boolean>(); // key is the buttonID, value is true or false if the button has been pressed in this instance

    private Context context;

    TextView scoreLabel; // this label should display current score
    TextView getEnteredLabel;

    Button newGameButton;

    private TextView enteredLabel; // this label displays the letters that have been entered so far ex: P O S E
    private TextView instructionsLabel; // maybe we should hide the instructions after a submit? be careful this doesn't alter the linear layout


    private Button b1; // represents the first button in the grid of letters (top left)
    private Button b2; // represents the second button in the grid of letters
    private Button b3; // etc...
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;
    private Button b11;
    private Button b12;
    private Button b13;
    private Button b14;
    private Button b15;
    private Button b16; // represents the last button in the grid of letters (bottom right)

    private Button previouslyPressed; // this represents the grid button that was most recently pressed
    private Button neverPressed; // represents when a button hasn't been pressed yet
    private Button adjacent[][] = new Button[4][4];

    private Button clearButton;
    private Button submitButton;




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
            Log.e(TAG,"Dictionary doesn't exist!!");
        }
        setContentView(R.layout.activity_main);
        generateLetters(); // #TODO repeat this line in the newGame function

        defaultButtonBackground(); // #TODO repeat this line in the newGame function

        context = getApplicationContext();

        scoreLabel = findViewById(R.id.scoreLabel);
        enteredLabel = findViewById(R.id.enteredLabel);
        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(this);

        neverPressed = new Button(this); // #TODO repeat this line and the two lines below in newGame function
        neverPressed.setText("never pressed");
        previouslyPressed = neverPressed;

        adjacent = new Button[][]{{b1, b2, b3, b4}, {b5, b6, b7, b8}, {b9, b10, b11, b12}, {b13, b14, b15, b16}}; // this 2d array is an array of buttons to calculate which is adjacent

    }

    public String generateRandLetter (){
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        String s = Character.toString(c);
        return s;
    }

    public void generateLetters(){


        b1 = (Button)findViewById(R.id.button1); // find button
        b1.setText(generateRandLetter()); // generate random letter
        b1.setOnClickListener(this); // set OnClickListener
        hasBeenPressed.put(b1, false); // Button hasn't been pressed yet (default state)
        b2 = (Button)findViewById(R.id.button2);
        b2.setText(generateRandLetter());
        b2.setOnClickListener(this);
        hasBeenPressed.put(b2, false);
        b3 = (Button)findViewById(R.id.button3);
        b3.setText(generateRandLetter());
        b3.setOnClickListener(this);
        hasBeenPressed.put(b3, false);
        b4 = (Button)findViewById(R.id.button4);
        b4.setText(generateRandLetter());
        b4.setOnClickListener(this);
        hasBeenPressed.put(b4, false);
        b5 = (Button)findViewById(R.id.button5);
        b5.setText(generateRandLetter());
        b5.setOnClickListener(this);
        hasBeenPressed.put(b5, false);
        b6 = (Button)findViewById(R.id.button6);
        b6.setText(generateRandLetter());
        b6.setOnClickListener(this);
        hasBeenPressed.put(b6, false);
        b7 = (Button)findViewById(R.id.button7);
        b7.setText(generateRandLetter());
        b7.setOnClickListener(this);
        hasBeenPressed.put(b7, false);
        b8 = (Button)findViewById(R.id.button8);
        b8.setText(generateRandLetter());
        b8.setOnClickListener(this);
        hasBeenPressed.put(b8, false);
        b9 = (Button)findViewById(R.id.button9);
        b9.setText(generateRandLetter());
        b9.setOnClickListener(this);
        hasBeenPressed.put(b9, false);
        b10 = (Button)findViewById(R.id.button10);
        b10.setText(generateRandLetter());
        b10.setOnClickListener(this);
        hasBeenPressed.put(b10, false);
        b11 = (Button)findViewById(R.id.button11);
        b11.setText(generateRandLetter());
        b11.setOnClickListener(this);
        hasBeenPressed.put(b11, false);
        b12 = (Button)findViewById(R.id.button12);
        b12.setText(generateRandLetter());
        b12.setOnClickListener(this);
        hasBeenPressed.put(b12, false);
        b13 = (Button)findViewById(R.id.button13);
        b13.setText(generateRandLetter());
        b13.setOnClickListener(this);
        hasBeenPressed.put(b13, false);
        b14 = (Button)findViewById(R.id.button14);
        b14.setText(generateRandLetter());
        b14.setOnClickListener(this);
        hasBeenPressed.put(b14, false);
        b15 = (Button)findViewById(R.id.button15);
        b15.setText(generateRandLetter());
        b15.setOnClickListener(this);
        hasBeenPressed.put(b15, false);
        b16 = (Button)findViewById(R.id.button16);
        b16.setText(generateRandLetter());
        b16.setOnClickListener(this);
        hasBeenPressed.put(b16, false);
    }

    private boolean validEntry(Button b) { // this method takes as input the button that has been pressed and checks if it is a valid entry based on the previously pressed button
        if (previouslyPressed.getText() == "never pressed") { // no button has been pressed in the current sequence
            return true;
        }

        if (b == previouslyPressed) { // the button just pressed was the same button previously pressed, not valid
            return false;
        }

        for (int x = 0; x < 4; x++) { // find the correct button in the adjacent array
            for (int y = 0; y < 4; y++) {
                if (adjacent[x][y] == b) {
                    if (x > 0) { // don't go out of array bounds
                        if (adjacent[x-1][y] == previouslyPressed) { // to the left of current button
                            return true;
                        }
                    }

                    if (y > 0) { // don't go out of array bounds
                        if (adjacent[x][y-1] == previouslyPressed) { // above current button
                            return true;
                        }
                    }

                    if (y > 0 && x > 0) {
                        if (adjacent[x-1][y-1] == previouslyPressed) { // top left of current button
                            return true;
                        }
                    }

                    if (y > 0 && x < 3) {
                        if (adjacent[x+1][y-1] == previouslyPressed) { // top right of current button
                            return true;
                        }
                    }

                    if (x < 3) {
                        if (adjacent[x+1][y] == previouslyPressed) { // right of current button
                            return true;
                        }
                    }

                    if (x < 3 && y < 3) {
                        if (adjacent[x+1][y+1] == previouslyPressed) { // bottom right of current button
                            return true;
                        }
                    }

                    if (y < 3) {
                        if (adjacent[x][y+1] == previouslyPressed) { // below current button
                            return true;
                        }
                    }

                    if (y < 3 && x > 0) {
                        if (adjacent[x-1][y+1] == previouslyPressed) { // bottom left of current button
                            return true;
                        }
                    }

                }
            }
        }

        return false;
    }

    public void defaultButtonBackground() {
        b1.setBackgroundResource(android.R.drawable.btn_default);
        b2.setBackgroundResource(android.R.drawable.btn_default);
        b3.setBackgroundResource(android.R.drawable.btn_default);
        b4.setBackgroundResource(android.R.drawable.btn_default);
        b5.setBackgroundResource(android.R.drawable.btn_default);
        b6.setBackgroundResource(android.R.drawable.btn_default);
        b7.setBackgroundResource(android.R.drawable.btn_default);
        b8.setBackgroundResource(android.R.drawable.btn_default);
        b9.setBackgroundResource(android.R.drawable.btn_default);
        b10.setBackgroundResource(android.R.drawable.btn_default);
        b11.setBackgroundResource(android.R.drawable.btn_default);
        b12.setBackgroundResource(android.R.drawable.btn_default);
        b13.setBackgroundResource(android.R.drawable.btn_default);
        b14.setBackgroundResource(android.R.drawable.btn_default);
        b15.setBackgroundResource(android.R.drawable.btn_default);
        b16.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void onClick(View v) {
        // required method stub for View.OnClickListener
        Log.e(TAG, "inside onClick function in MainActivity");
        String message = "";

        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;
        Toast toast;


        switch (v.getId()) { // switch statement determines which of the letter buttons have been pressed

            case R.id.clearButton:

                enteredLabel.setText(""); // clear the text from the enteredLabel
                previouslyPressed = neverPressed;
                hasBeenPressed.put(b1, false); // set all hasBeenPressed to false
                hasBeenPressed.put(b2, false);
                hasBeenPressed.put(b3, false);
                hasBeenPressed.put(b4, false);
                hasBeenPressed.put(b5, false);
                hasBeenPressed.put(b6, false);
                hasBeenPressed.put(b7, false);
                hasBeenPressed.put(b8, false);
                hasBeenPressed.put(b9, false);
                hasBeenPressed.put(b10, false);
                hasBeenPressed.put(b11, false);
                hasBeenPressed.put(b12, false);
                hasBeenPressed.put(b13, false);
                hasBeenPressed.put(b14, false);
                hasBeenPressed.put(b15, false);
                hasBeenPressed.put(b16, false);

                defaultButtonBackground();


                Log.e(TAG, "clear button pressed");

                break;

            case R.id.submitButton:
                // #TODO handle submit stuff here
                // #TODO All words must utilize a minimum of two vowels.
                // #TODO You cannot generate the same word more than once, even if it’s from different letters.
                Log.e(TAG, "submit button pressed");

                break;

            case R.id.newGameButton:
                // #TODO do new game stuff here
                Log.e(TAG, "new game");
                break;

            case R.id.button1:
                message += b1.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b1)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b1)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b1, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b1.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b1; // this is now the most recently pressed button
                        b1.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button2:
                message += b2.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b2)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b2)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b2, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b2.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b2; // this is now the most recently pressed button
                        b2.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button3:
                message += b3.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b3)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b3)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b3, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b3.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b3; // this is now the most recently pressed button
                        b3.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button4:
                message += b4.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b4)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b4)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b4, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b4.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b4; // this is now the most recently pressed button
                        b4.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button5:
                message += b5.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b5)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b5)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b5, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b5.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b5; // this is now the most recently pressed button
                        b5.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button6:
                message += b6.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b6)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b6)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b6, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b6.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b6; // this is now the most recently pressed button
                        b6.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button7:
                message += b7.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b7)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b7)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b7, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b7.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b7; // this is now the most recently pressed button
                        b7.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button8:
                message += b8.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b8)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b8)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b8, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b8.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b8; // this is now the most recently pressed button
                        b8.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button9:
                message += b9.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b9)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b9)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b9, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b9.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b9; // this is now the most recently pressed button
                        b9.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button10:
                message += b10.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b10)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b10)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b10, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b10.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b10; // this is now the most recently pressed button
                        b10.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button11:
                message += b11.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b11)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b11)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b11, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b11.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b11; // this is now the most recently pressed button
                        b11.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button12:
                message += b12.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b12)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b12)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b12, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b12.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b12; // this is now the most recently pressed button
                        b12.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button13:
                message += b13.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b13)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b13)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b13, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b13.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b13; // this is now the most recently pressed button
                        b13.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button14:
                message += b14.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b14)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b14)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b14, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b14.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b14; // this is now the most recently pressed button
                        b14.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button15:
                message += b15.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b15)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b15)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b15, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b15.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b15; // this is now the most recently pressed button
                        b15.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            case R.id.button16:
                message += b16.getText().toString();
                message += b16.getText().toString(); // for logging purposes
                if (hasBeenPressed.get(b16)) { // has the button been pressed yet this sequence?
                    // show the user somehow that this is not a valid input because it has already been pressed
                    text = "This button has already been pressed";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    if (validEntry(b16)) { // check if this is a valid entry based on the adjacencies
                        hasBeenPressed.put(b16, true); // mark that it has been pressed
                        String currentLabel = enteredLabel.getText().toString(); // update the on screen label
                        currentLabel += b16.getText().toString().toUpperCase(); // make sure it's uppercase
                        currentLabel += " "; // account for proper spacing
                        enteredLabel.setText(currentLabel);
                        previouslyPressed = b16; // this is now the most recently pressed button
                        b16.setBackgroundColor(0xFF3399FF); // change button color so the user can tell it has been pressed
                    }
                    else {
                        // show the user that this button isn't valid because it isn't adjacent
                        text = "Invalid input, must be adjacent to previous character";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;

            default:
                Log.e(TAG, "unknown click");
                break;
        }

        message += " button has been pressed";
        Log.e(TAG, message);

    }
}
