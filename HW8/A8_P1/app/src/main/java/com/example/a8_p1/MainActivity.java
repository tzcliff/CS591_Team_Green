package com.example.a8_p1;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    TextView label; // display our fact on this label

    final String TAG = "Boston";

    LinkedList<String> facts = new LinkedList<>(); // keep a local copy of our facts after getting them from the database
    // this way if the user scrolls many times, we only call the database 5 times regardless.

    int pointer = 0; // pointer to keep track of which fact we're on


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = findViewById(R.id.label);

        LinkedList<String> list = new LinkedList<>(); // a linked list will easily allow us to cycle between facts

        list.add("fact1"); // add the database keys for all our facts
        list.add("fact2");
        list.add("fact3");
        list.add("fact4");
        list.add("fact5");

        /*
            Note my design choice:

            Rather than grabbing a fact from the database every time the user hits next or previous,
            I chose to grab all elements from the database during onCreate. Then we keep local track
            of those facts with the facts LinkedList and when the user hits next or previous we just
            iterate through the facts.
         */



        // get database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        for (int x = 0; x < 5; x++) {
            DatabaseReference myRef = database.getReference(list.get(x)); // for each fact in the database


            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    facts.add(value); // keep a linked list of these facts so we only have to check the database during onCreate
                    Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                    facts.add("Failed to read value."); // let the user know there was some error here
                }
            });

            Log.e(TAG, facts.toString());

        }


    }

    public void next(View view) {
        pointer++; // increment the pointer
        if (pointer == 5) { // if we're at the last fact we need to loop back around to the beginning
            pointer = 0;
        }
        Log.i(TAG, "facts list: " + facts.toString());
        Log.i(TAG, "next: " + String.valueOf(pointer));

        label.setText(facts.get(pointer)); // display the fact


    }

    public void previous(View view) {
        pointer--; // decrement the pointer
        if (pointer == -1) { // if we're at the first fact we need to loop around to the end
            pointer = 4;
        }
        Log.i(TAG, "facts list: " + facts.toString());
        Log.i(TAG, "previous: " + String.valueOf(pointer));


        label.setText(facts.get(pointer)); // display the fact

    }
}
