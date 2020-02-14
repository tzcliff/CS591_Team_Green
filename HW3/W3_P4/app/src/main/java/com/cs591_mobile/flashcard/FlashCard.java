package com.cs591_mobile.flashcard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FlashCard extends AppCompatActivity{

    private TextView txtnum1, txtnum2, txtYourAns, txtInteraction;
    private EditText editAns;
    private Button btnSubmit, btnGenerate;
    private Integer int1, int2, correctResult = 0, count = 0, correctCount = 0;
    private Boolean generated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        txtnum1 = (TextView)findViewById(R.id.txtnum1);
        txtnum2 = (TextView)findViewById(R.id.txtnum2);
        txtYourAns = (TextView)findViewById(R.id.txtYourAns);
        txtInteraction = (TextView)findViewById(R.id.txtInteraction);
        editAns = (EditText)findViewById(R.id.editAns);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnGenerate = (Button)findViewById(R.id.btnGenerate);
        if(!generated){
            btnSubmit.setEnabled(false);
        }
        else{
            btnSubmit.setEnabled(true);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("correctCount", correctCount);
        outState.putInt("int1", int1);
        outState.putInt("int2", int2);
        outState.putInt("correctResult", correctResult);
        outState.putInt("count", count);
        outState.putBoolean("generated", generated);


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        correctCount = savedInstanceState.getInt("correctCount");
        int1 = savedInstanceState.getInt("int1");
        int2 = savedInstanceState.getInt("int2");
        correctResult = savedInstanceState.getInt("correctResult");
        count = savedInstanceState.getInt("count");
        generated = savedInstanceState.getBoolean("generated");
        txtnum1.setText(String.valueOf(int1));
        txtnum2.setText("÷ " + String.valueOf(int2));
        if(!generated){
            btnSubmit.setEnabled(false);
        }
        else{
            btnSubmit.setEnabled(true);
        }
    }

    public void generateMathQuestion(){
        int1 = (int)(Math.random() * 100 + 1);
        int2 = (int)(Math.random() * 100 + 1);

        while (int1 % int2 != 0) {
            int1 = (int)(Math.random() * 100 + 1);
            int2 = (int)(Math.random() * 100 + 1);
        }

        txtnum1.setText(String.valueOf(int1));
        txtnum2.setText("÷ " + String.valueOf(int2));
        correctResult = int1 / int2;
    }
    public void buttonHelper(View view) {
        switch(view.getId()) {
            case R.id.btnGenerate:
                generateMathQuestion();
                Toast.makeText(getApplicationContext(),"10 new questions generated",Toast.LENGTH_LONG).show();
                generated = true;
                btnSubmit.setEnabled(true);

                break;
            case R.id.btnSubmit:
                try {
                    if (correctResult == Integer.valueOf(editAns.getText().toString())) {
                        txtInteraction.setText("You Are Correct!");
                        correctCount++;
                        count++;
                    } else {
                        txtInteraction.setText("You Are Wrong, Do Better!");
                        count++;
                    }
                    if (count == 10) {
                        Toast.makeText(getApplicationContext(), "You have got " + correctCount.toString() + " out of 10", Toast.LENGTH_LONG).show();
                        count = 0;
                        correctCount = 0;
                        btnSubmit.setEnabled(false);
                    } else {
                        generateMathQuestion();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Input!",Toast.LENGTH_LONG).show();
                }

                break;
        }

    }
}
