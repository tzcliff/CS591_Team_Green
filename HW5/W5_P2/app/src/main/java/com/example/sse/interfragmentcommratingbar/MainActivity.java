package com.example.sse.interfragmentcommratingbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends Activity implements TopFragment.ControlFragmentListener, BotFragment.ControlFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void sendDirection(String direction) {
        int x = new Integer(0);
        BotFragment botFragment = (BotFragment)getFragmentManager().findFragmentById(R.id.botFragment);
        if(direction.equals("left")){
            Log.i("TAG", "LEFT");
            x = botFragment.getCurrDrawableIndex();
            if (x == 0){
                x = botFragment.drawables.size() -1;
            }
            else{
            x--;}
            botFragment.setCurrDrawableIndex(x);
            botFragment.changePicture();

        }
        else{
            Log.i("TAG", "RIGHT");
            x = botFragment.getCurrDrawableIndex();
            if (x == botFragment.drawables.size() - 1){
                x = 0;}
            else{
            x++;}
            botFragment.setCurrDrawableIndex(x);
            botFragment.changePicture();
        }

    }
}
