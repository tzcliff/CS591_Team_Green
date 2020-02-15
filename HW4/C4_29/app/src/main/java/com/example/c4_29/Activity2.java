package com.example.c4_29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private String language;
    private TextView tv;
    private RelativeLayout rl;
    private RelativeLayout.LayoutParams RLP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        language = getIntent().getStringExtra("language");
        tv = new TextView(this);

        switch (language) {
            case "Chinese":
                tv.setText("你好，世界");
                break;
            case "French":
                tv.setText("Bonjour le monde");
                break;
            case "Spanish":
                tv.setText("Hola Mundo");
                break;
            case "Japanese":
                tv.setText("こんにちは世界");
                break;
            case "Korean":
                tv.setText("안녕, 세상");
                break;
        }

        rl = findViewById(R.id.rltLayout);
        RLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(RLP);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);


        rl.addView(tv);



    }
}
