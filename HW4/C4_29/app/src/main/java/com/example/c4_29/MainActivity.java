package com.example.c4_29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnC;
    Button btnF;
    Button btnK;
    Button btnJ;
    Button btnS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnC = (Button) findViewById(R.id.btnC);
        btnF = (Button) findViewById(R.id.btnF);
        btnK = (Button) findViewById(R.id.btnK);
        btnJ = (Button) findViewById(R.id.btnJ);
        btnS = (Button) findViewById(R.id.btnS);

        View.OnClickListener mListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                switch (v.getId()) {
                    case R.id.btnC:
                        intent.putExtra("language", "Chinese");
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.btnF:
                        intent.putExtra("language", "French");
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.btnK:
                        intent.putExtra("language", "Korean");
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.btnJ:
                        intent.putExtra("language", "Japanese");
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.btnS:
                        intent.putExtra("language", "Spanish");
                        MainActivity.this.startActivity(intent);
                        break;
                }
            }
        };

        btnC.setOnClickListener(mListener);
        btnF.setOnClickListener(mListener);
        btnJ.setOnClickListener(mListener);
        btnS.setOnClickListener(mListener);
        btnK.setOnClickListener(mListener);

    }
}
