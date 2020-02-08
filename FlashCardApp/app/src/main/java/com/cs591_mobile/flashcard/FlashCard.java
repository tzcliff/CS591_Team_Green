package com.cs591_mobile.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity{

    private TextView txtnum1, txtnum2, txtYourAns, txtInteraction;
    private EditText editAns;
    private Button btnSubmit, btnGenerate;
    private Integer int1, int2, correctResult, count, correctCount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        txtnum1 = (TextView)findViewById(R.id.txtnum1);
        txtnum2 = (TextView)findViewById(R.id.txtnum2);
        txtYourAns = (TextView)findViewById(R.id.txtYourAns);
        txtInteraction = (TextView)findViewById(R.id.txtInteraction);
        editAns = (EditText)findViewById(R.id.editAns);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnGenerate = (Button)findViewById(R.id.btnGenerate);

        count = 0;
        correctCount = 0;
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

                break;
            case R.id.btnSubmit:
                if (correctResult == Integer.valueOf(editAns.getText().toString())) {
                    txtInteraction.setText("You Are Correct!");
                    correctCount++;
                    count++;
                }
                else{
                    txtInteraction.setText("You Are Wrong, Do Better!");
                    count++;
                }
                if (count == 10){
                    Toast.makeText(getApplicationContext(),"You have got " + correctCount.toString() + " out of 10",Toast.LENGTH_LONG).show();
                }
                else{
                    generateMathQuestion();
                }

                break;
        }

    }
}
