package com.example.boggle.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.example.boggle.R;


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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView enteredLabel; // this label displays the letters that have been entered so far ex: P O S E
    private TextView instructionsLabel; // maybe we should hide the instructions after a submit? be careful this doesn't alter the linear layout

    private Button clearButton;
    private Button submitButton;

    private Button button1; // represents the first button in the grid of letters (top left)
    private Button button2; // represents the second button in the grid of letters
    private Button button3; // etc...
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;
    private Button button16; // represents the last button in the grid of letters (bottom right)

    public TopFragment() {
        // Required empty public constructor
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

        instructionsLabel = view.findViewById(R.id.instructionsLabel);
        enteredLabel = view.findViewById(R.id.enteredLabel);

        clearButton = view.findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);
        submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        button1 = view.findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = view.findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = view.findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = view.findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button6 = view.findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button7 = view.findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button8 = view.findViewById(R.id.button8);
        button8.setOnClickListener(this);
        button9 = view.findViewById(R.id.button9);
        button9.setOnClickListener(this);
        button10 = view.findViewById(R.id.button10);
        button10.setOnClickListener(this);
        button11 = view.findViewById(R.id.button11);
        button11.setOnClickListener(this);
        button12 = view.findViewById(R.id.button12);
        button12.setOnClickListener(this);
        button13 = view.findViewById(R.id.button13);
        button13.setOnClickListener(this);
        button14 = view.findViewById(R.id.button14);
        button14.setOnClickListener(this);
        button15 = view.findViewById(R.id.button15);
        button15.setOnClickListener(this);
        button16 = view.findViewById(R.id.button16);
        button16.setOnClickListener(this);

        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    public void onClick(View v) {
        // required method stub for View.OnClickListener

        String message = "";

        switch (v.getId()) { // switch statement determines which of the letter buttons have been pressed

            case R.id.clearButton:
                // #TODO do clear stuff here
                Log.e("tag", "clear button pressed");

            case R.id.submitButton:
                // #TODO handle submit stuff here
                Log.e("tag", "submit button pressed");

            case R.id.button1:
                message += button1.getText().toString();
                break;

            case R.id.button2:
                message += button2.getText().toString();
                break;

            case R.id.button3:
                message += button3.getText().toString();
                break;

            case R.id.button4:
                message += button4.getText().toString();
                break;

            case R.id.button5:
                message += button5.getText().toString();
                break;

            case R.id.button6:
                message += button6.getText().toString();
                break;

            case R.id.button7:
                message += button7.getText().toString();
                break;

            case R.id.button8:
                message += button8.getText().toString();
                break;

            case R.id.button9:
                message += button9.getText().toString();
                break;

            case R.id.button10:
                message += button10.getText().toString();
                break;

            case R.id.button11:
                message += button11.getText().toString();
                break;

            case R.id.button12:
                message += button12.getText().toString();
                break;

            case R.id.button13:
                message += button13.getText().toString();
                break;

            case R.id.button14:
                message += button14.getText().toString();
                break;

            case R.id.button15:
                message += button15.getText().toString();
                break;

            case R.id.button16:
                message += button16.getText().toString();
                break;

            default:
                break;
        }

        message += "button has been pressed";
        Log.e("tag", message);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    }
}
