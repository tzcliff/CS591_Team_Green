package com.example.trafficlight;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvLight;
    private Button button;
    private String color = "#FF0000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLight = (TextView) findViewById(R.id.tvLight);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (color) {
                    case "#FF0000" :
                        color = "#00FF00";
                        break;
                    case "#00FF00" :
                        color = "#FFFF00";
                        break;
                    case "#FFFF00" :
                        color = "#FF0000";
                        break;
                }
                tvLight.setBackgroundColor(Color.parseColor(color));
            }
        });
    }
}
