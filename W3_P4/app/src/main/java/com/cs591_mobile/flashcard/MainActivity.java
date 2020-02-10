package com.cs591_mobile.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TextView txtUser, txtPass;
    private EditText editUser, editPass;
    private Button btnSubmit;
    private List<User> userList = new ArrayList<User>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = (EditText)findViewById(R.id.editUser);
        editPass = (EditText)findViewById(R.id.editPass);
        txtUser = (TextView)findViewById(R.id.txtUser);
        txtPass = (TextView)findViewById(R.id.editPass);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        userList.add(new User("a","1"));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < userList.size(); i ++) {
                    if (userList.get(i).isValid(editUser.getText().toString(),editPass.getText().toString())) {
                        Intent second = new Intent(MainActivity.this, FlashCard.class);
                        Toast.makeText(getApplicationContext(),"Welcome " + userList.get(i).getUsername(),Toast.LENGTH_LONG).show();
                        MainActivity.this.startActivity(second);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Wrong password or login",Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }
}




