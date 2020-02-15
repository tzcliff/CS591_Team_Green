package com.example.w4_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    EditText textBox;
    TextView usd;
    TextView yen;
    TextView rupi;
    TextView florin;
    private GestureDetector GD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBox = findViewById(R.id.euroEdit);
        usd = findViewById(R.id.usdView);
        yen = findViewById(R.id.yenView);
        rupi = findViewById(R.id.rupiView);
        florin = findViewById(R.id.florinView);

        textBox.addTextChangedListener(new TextWatcher() { // when the Edit Text experiences changes:

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                // every time the text changes, update all the values
                try {
                    double euros = Double.valueOf(textBox.getText().toString()); // double value representing the euros entered
                    final double usdConst = 1.08382; // one euro s 1.08382 USD as of 2/14/20
                    final double yenConst = 118.977; // one euro is 118.977 yen as of 2/14/20
                    final double rupiConst = 77.5996; // one euro is 77.5996 Indian Rupees as of 2/14/20
                    final double florinConst = 1.91000; // one euro is 1/.91000 Aruban Florins as of 2/14/20

                    usd.setText(String.format("%.2f USD", (euros * usdConst)));
                    yen.setText(String.format("%.2f Yen", (euros * yenConst)));
                    rupi.setText(String.format("%.2f Rupi", (euros * rupiConst)));
                    florin.setText(String.format("%.2f Florin", (euros * florinConst)));
                }
                catch(Exception ex) {
                    usd.setText("0.00 USD");
                    yen.setText("0.00 Yen");
                    florin.setText("0.00 Florin");
                    rupi.setText("0.00 Rupi");
                }

            }
        });

        GD = new GestureDetector(this, this);   //Context, Listener as per Constructor Doc.

    }

    //////////////////////////////////////////////////////////////////////////
    //very important step, otherwise we won't be able to capture our touches//
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);               //Our GD will not automatically receive Android Framework Touch notifications.
        // Insert this line to consume the touch event locally by our GD,
        // IF YOU DON'T insert this before the return, our GD will not receive the event, and therefore won't do anything.
        return super.onTouchEvent(event);          // Do this last, why?
    }
    //////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        try {
            if (textBox.getText().toString().equals("")) {
                if (distanceY < 0) {
                    double currentVal = 0.0;
                    currentVal = currentVal + 0.05;
                    String result = String.format("%.2f", currentVal);
                    textBox.setText(result);

                }
                else {
                    double currentVal = 0.0;
                    currentVal = currentVal - 0.05;
                    String result = String.format("%.2f", currentVal);
                    textBox.setText(result);

                }

            }
            else {
                if (distanceY < 0) {
                    double currentVal = Double.valueOf(textBox.getText().toString());
                    currentVal = currentVal + 0.05;
                    String result = String.format("%.2f", currentVal);
                    textBox.setText(result);

                }
                else {
                    double currentVal = Double.valueOf(textBox.getText().toString());
                    currentVal = currentVal - 0.05;
                    String result = String.format("%.2f", currentVal);
                    textBox.setText(result);

                }
            }

        }
        catch(Exception ex) {
            Context context = getApplicationContext();
            CharSequence text = "onFling Exception!" + ex;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            System.out.println("onFling exception: " + ex);
        }

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (textBox.getText().toString().equals("")) {
                if (velocityY < 0) {
                    double currentVal = 0.0;
                    currentVal = currentVal + 1.0;
                    String result = String.format("%.2f", currentVal);
                    textBox.setText(result);
                }
                else {
                    double currentVal = 0.0;
                    currentVal = currentVal - 1.0;
                    String result = String.format("%.2f", currentVal);
                    textBox.setText(result);
                }
            }
            else {
                if (velocityY < 0) {
                    double currentVal = Double.valueOf(textBox.getText().toString());
                    currentVal = currentVal + 1.0;
                    String result = String.format("%.2f", currentVal);
                    textBox.setText(result);
                }
                else {
                    double currentVal = Double.valueOf(textBox.getText().toString());
                    currentVal = currentVal - 1.0;
                    String result = String.format("%.2f", currentVal);
                    textBox.setText(result);
                }

            }
//            if (textBox.getText().toString().equals("")) {
//                if (velocityY < -1000) {
//                    double currentVal = 0.0;
//                    currentVal = currentVal + 1.0;
//                    String result = String.format("%.2f", currentVal);
//                    textBox.setText(result);
//                }
//                else if (velocityY < 0 && velocityY > -1000){
//                    double currentVal = 0.0;
//                    currentVal = currentVal + 0.05;
//                    String result = String.format("%.2f", currentVal);
//                    textBox.setText(result);
//                }
//                else if (velocityY >= 0 && velocityY < 1000) {
//                    double currentVal = 0.0;
//                    currentVal = currentVal - 0.05;
//                    String result = String.format("%.2f", currentVal);
//                    textBox.setText(result);
//                }
//                else {
//                    double currentVal = 0.0;
//                    currentVal = currentVal - 1.0;
//                    String result = String.format("%.2f", currentVal);
//                    textBox.setText(result);
//                }
//
//            }
//            else {
//                if (velocityY < -1000) {
//                    double currentVal = Double.valueOf(textBox.getText().toString());
//                    currentVal = currentVal + 1.0;
//                    String result = String.format("%.2f", currentVal);
//                    textBox.setText(result);
//                }
//                else if (velocityY < 0 && velocityY > -1000){
//                    double currentVal = Double.valueOf(textBox.getText().toString());
//                    currentVal = currentVal + 0.05;
//                    String result = String.format("%.2f", currentVal);
//                    textBox.setText(result);
//                }
//                else if (velocityY >= 0 && velocityY < 1000) {
//                    double currentVal = Double.valueOf(textBox.getText().toString());
//                    currentVal = currentVal - 0.05;
//                    String result = String.format("%.2f", currentVal);
//                    textBox.setText(result);
//                }
//                else {
//                    double currentVal = Double.valueOf(textBox.getText().toString());
//                    currentVal = currentVal - 1.0;
//                    String result = String.format("%.2f", currentVal);
//                    textBox.setText(result);
//                }
//            }

        }
        catch(Exception ex) {
            Context context = getApplicationContext();
            CharSequence text = "onFling Exception!" + ex;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            System.out.println("onFling exception: " + ex);
        }
        return true;
    }




}
