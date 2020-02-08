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

    private Double value_fahrenheit;
    private Double value_celsius;
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


        seekBar_fahrenheit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int fahrenheit, boolean b) {
                valueText_fahrenheit.setText(String.valueOf(fahrenheit)+" F");
                value_celsius = tempConverter.fToC(Double.valueOf(String.valueOf(fahrenheit)));
                if(value_celsius > 0){
                    seekBar_celsius.setProgress((int)Math.round(value_celsius));
                }
                else{
                    seekBar_celsius.setProgress(0);
                    valueText_celsius.setText(String.valueOf((int)Math.round(value_celsius)) + " C");
                }
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
                value_fahrenheit = tempConverter.cToF(Double.valueOf(String.valueOf(celsius)));
                if(value_fahrenheit < 100){
                    seekBar_fahrenheit.setProgress((int)Math.round(value_fahrenheit));
                }
                else{
                    seekBar_fahrenheit.setProgress(100);
                    valueText_fahrenheit.setText(String.valueOf((int)Math.round(value_fahrenheit))+" F");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
