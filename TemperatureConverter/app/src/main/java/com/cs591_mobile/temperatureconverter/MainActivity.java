package com.cs591_mobile.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cs591_mobile.temperatureconverter.models.TempConverter;

public class MainActivity extends AppCompatActivity {

    private TextView txt_message;
    private TextView valueText_fahrenheit;
    private TextView valueText_celsius;
    private SeekBar seekBar_fahrenheit;
    private SeekBar seekBar_celsius;

    private Double value_fahrenheit = 32.0;
    private Double value_celsius = 0.0;
    private TempConverter tempConverter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempConverter = new TempConverter();
        txt_message = (TextView) findViewById(R.id.label_message);
        valueText_fahrenheit = (TextView) findViewById(R.id.valueText_fahrenheit);
        valueText_celsius = (TextView) findViewById(R.id.valueText_celsius);
        seekBar_fahrenheit = (SeekBar) findViewById(R.id.seekBar_fahrenheit);
        seekBar_celsius = (SeekBar) findViewById(R.id.seekBar_celsius);

        seekBar_celsius.setProgress((int)Math.round(value_celsius));
        valueText_celsius.setText(String.valueOf((int)Math.round(value_celsius)) + " C");
        seekBar_fahrenheit.setProgress((int)Math.round(value_fahrenheit));
        valueText_fahrenheit.setText(String.valueOf((int)Math.round(value_fahrenheit))+" F");


        txt_message.setText("It's cold!!!");

        seekBar_fahrenheit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int fahrenheit, boolean b) {
                valueText_fahrenheit.setText(String.valueOf(fahrenheit)+" F");
                value_fahrenheit =  Double.valueOf(String.valueOf(fahrenheit));
                value_celsius = tempConverter.fToC(value_fahrenheit);
                if(value_celsius > 0){
                    seekBar_celsius.setProgress((int)Math.round(value_celsius));
                }
                else{
                    seekBar_celsius.setProgress(0);
                    valueText_celsius.setText(String.valueOf((int)Math.round(value_celsius)) + " C");
                }
                updateMessageText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_celsius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int celsius, boolean b) {
                valueText_celsius.setText(String.valueOf(celsius)+" C");
                value_celsius = Double.valueOf(String.valueOf(celsius));
                value_fahrenheit = tempConverter.cToF(value_celsius);
                if(value_fahrenheit < 100){
                    seekBar_fahrenheit.setProgress((int)Math.round(value_fahrenheit));
                }
                else{
                    seekBar_fahrenheit.setProgress(100);
                    valueText_fahrenheit.setText(String.valueOf((int)Math.round(value_fahrenheit))+" F");
                }
                updateMessageText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
    public void updateMessageText(){
        if(value_celsius < 12){
            txt_message.setText("It's cold!!!");
        }
        else{
            txt_message.setText("It's not cold!!!");
        }
    }
}
