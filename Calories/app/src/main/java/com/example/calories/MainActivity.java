package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txtCal;
    private Button btnSubmit;
    private EditText editBurger, editSandwich, editChicken, editFries, editCola;
    private Integer totalCal, numBurger, numSandwich, numChicken, numFries, numCola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCal = 0;
        txtCal = findViewById(R.id.txtCal);
        txtCal.setText(String.valueOf(totalCal));

        numBurger = 0;
        numSandwich = 0;
        numChicken = 0;
        numFries = 0;
        numCola = 0;

        editBurger = findViewById(R.id.editBurger);
        editSandwich = findViewById(R.id.editSandwich);
        editChicken = findViewById(R.id.editChicken);
        editFries = findViewById(R.id.editFries);
        editCola = findViewById(R.id.editCola);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        numBurger = helper(editBurger);
        numSandwich = helper(editSandwich);
        numChicken = helper(editChicken);
        numFries = helper(editFries);
        numCola = helper(editCola);
        totalCal = 1000 * numBurger + 600 * numSandwich + 700 * numChicken + 400 * numFries + 300 * numCola;
        txtCal.setText(String.valueOf(totalCal));
    }

    public int helper(EditText edit) {
        if (edit.getText().toString().equals("")) return 0;
        return Integer.parseInt(edit.getText().toString());
    }
}