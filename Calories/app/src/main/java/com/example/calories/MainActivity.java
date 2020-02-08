package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements OnCheckedChangeListener {
    private TextView txtCal;
    private CheckBox chkBurger, chkSandwich, chkChicken, chkFries, chkCola;
    private Integer totalCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCal = 0;
        txtCal = findViewById(R.id.txtCal);
        txtCal.setText(String.valueOf(totalCal));

        chkBurger = findViewById(R.id.chkBurger);
        chkSandwich = findViewById(R.id.chkSandwich);
        chkChicken = findViewById(R.id.chkChicken);
        chkFries = findViewById(R.id.chkFries);
        chkCola = findViewById(R.id.chkCola);

        chkBurger.setOnCheckedChangeListener(this);
        chkSandwich.setOnCheckedChangeListener(this);
        chkChicken.setOnCheckedChangeListener(this);
        chkFries.setOnCheckedChangeListener(this);
        chkCola.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton checkBox, boolean checked) {
        switch(checkBox.getId()) {
            case R.id.chkBurger:
                if (checked) totalCal += 1000;
                else totalCal -= 1000;
                break;
            case R.id.chkSandwich:
                if (checked) totalCal += 600;
                else totalCal -= 600;
                break;
            case R.id.chkChicken:
                if (checked) totalCal += 700;
                else totalCal -= 700;
                break;
            case R.id.chkFries:
                if (checked) totalCal += 400;
                else totalCal -= 400;
                break;
            case R.id.chkCola:
                if (checked) totalCal += 300;
                else totalCal -= 300;
                break;
            default:
                break;
        }
        txtCal.setText(String.valueOf(totalCal));
    }
}
