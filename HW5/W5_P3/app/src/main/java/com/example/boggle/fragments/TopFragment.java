package com.example.boggle.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.ColorInt;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.example.boggle.R;

import java.util.HashMap;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private HashMap<Button, Boolean> hasBeenPressed = new HashMap<Button, Boolean>(); // key is the buttonID, value is true or false if the button has been pressed in this instance
    public TextView enteredLabel; // this label displays the letters that have been entered so far ex: P O S E
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
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button previouslyPressed; // this represents the grid button that was most recently pressed
    private Button neverPressed; // represents when a button hasn't been pressed yet
    private Button adjacent[][] = new Button[4][4];
    private Button clearButton;
    private Button submitButton;
    private Context context;

    private OnFragmentInteractionListener mListener;

    public TopFragment() {
        // Required empty public constructor
    }

    public void newGameTop() {
        clearBoard();
        generateLetters(getView());
        defaultButtonBackground();
        previouslyPressed = neverPressed;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopFragment newInstance(String param1, String param2) {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_top, container, false);
        generateLetters(view);
        defaultButtonBackground();
        enteredLabel = view.findViewById(R.id.enteredLabel);
        clearButton = view.findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);
        submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        neverPressed = new Button(getActivity());
        neverPressed.setText("never pressed");
        previouslyPressed = neverPressed;

        adjacent = new Button[][]{{b1, b2, b3, b4}, {b5, b6, b7, b8}, {b9, b10, b11, b12}, {b13, b14, b15, b16}}; // this 2d array is an array of buttons to calculate which is adjacent
        context = getActivity();
        return view;
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



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.d("Jiang", "onAttach" );
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            Log.d("Jiang", "Ml" );
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    public void onAttach(Activity activity) {
        Log.d("Jiang", "onAttach" );
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
            Log.d("Jiang", "Ml" );
        } else {
            throw new RuntimeException(
                    " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onClick(View v) {
        // required method stub for View.OnClickListener



        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;
        Toast toast;


        switch (v.getId()) { // switch statement determines which of the letter buttons have been pressed

            case R.id.clearButton:

                clearBoard();

                break;

            case R.id.submitButton:

                mListener.submit();

                break;

            case R.id.button1:

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

                break;
        }




    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        public void submit();

    }

    public void clearBoard() {
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



    }

    public void generateLetters(View view){


       // b1 = (Button)getActivity().findViewById(R.id.button1); // find button
        b1 = view.findViewById(R.id.button1); // find button
        b1.setText(generateRandLetter()); // generate random letter
        b1.setOnClickListener(this); // set OnClickListener
        hasBeenPressed.put(b1, false); // Button hasn't been pressed yet (default state)
        b2 = view.findViewById(R.id.button2);
        b2.setText(generateRandLetter());
        b2.setOnClickListener(this);
        hasBeenPressed.put(b2, false);
        b3 = view.findViewById(R.id.button3);
        b3.setText(generateRandLetter());
        b3.setOnClickListener(this);
        hasBeenPressed.put(b3, false);
        b4 = view.findViewById(R.id.button4);
        b4.setText(generateRandLetter());
        b4.setOnClickListener(this);
        hasBeenPressed.put(b4, false);
        b5 = view.findViewById(R.id.button5);
        b5.setText(generateRandLetter());
        b5.setOnClickListener(this);
        hasBeenPressed.put(b5, false);
        b6 = view.findViewById(R.id.button6);
        b6.setText(generateRandLetter());
        b6.setOnClickListener(this);
        hasBeenPressed.put(b6, false);
        b7 = view.findViewById(R.id.button7);
        b7.setText(generateRandLetter());
        b7.setOnClickListener(this);
        hasBeenPressed.put(b7, false);
        b8 = view.findViewById(R.id.button8);
        b8.setText(generateRandLetter());
        b8.setOnClickListener(this);
        hasBeenPressed.put(b8, false);
        b9 = view.findViewById(R.id.button9);
        b9.setText(generateRandLetter());
        b9.setOnClickListener(this);
        hasBeenPressed.put(b9, false);
        b10 = view.findViewById(R.id.button10);
        b10.setText(generateRandLetter());
        b10.setOnClickListener(this);
        hasBeenPressed.put(b10, false);
        b11 = view.findViewById(R.id.button11);
        b11.setText(generateRandLetter());
        b11.setOnClickListener(this);
        hasBeenPressed.put(b11, false);
        b12 = view.findViewById(R.id.button12);
        b12.setText(generateRandLetter());
        b12.setOnClickListener(this);
        hasBeenPressed.put(b12, false);
        b13 = view.findViewById(R.id.button13);
        b13.setText(generateRandLetter());
        b13.setOnClickListener(this);
        hasBeenPressed.put(b13, false);
        b14 = view.findViewById(R.id.button14);
        b14.setText(generateRandLetter());
        b14.setOnClickListener(this);
        hasBeenPressed.put(b14, false);
        b15 = view.findViewById(R.id.button15);
        b15.setText(generateRandLetter());
        b15.setOnClickListener(this);
        hasBeenPressed.put(b15, false);
        b16 = view.findViewById(R.id.button16);
        b16.setText(generateRandLetter());
        b16.setOnClickListener(this);
        hasBeenPressed.put(b16, false);
    }

    public String generateRandLetter (){
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        String s = Character.toString(c);
        return s;
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
}
