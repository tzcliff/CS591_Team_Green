package com.example.sse.interfragmentcommunication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//public class MainActivity extends AppCompatActivity {
    public class MainActivity extends Activity implements ControlFragment.ControlFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendFood(Food food) {
        BottomFragment receivingFragment = (BottomFragment)getFragmentManager().findFragmentById(R.id.bottomFragment);
        receivingFragment.setFood(food);
    }
}


//Toast.makeText(getBaseContext(),"I would like to propose a Toast.", Toast.LENGTH_LONG).show();
