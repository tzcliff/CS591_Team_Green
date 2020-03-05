package com.example.sse.customlistview_sse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

public class DisplayWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_web);
        // Get the intent from MainActivity
        Intent intent = getIntent();
        String message = intent.getStringExtra("Name");
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(pickWeb(message));



    }

    /**
     * Map episode's name with URL string array created in resources strings.xml
     * @param name
     * @return
     */
    private String pickWeb(String name){
        String[] strings_name = this.getApplicationContext().getResources().getStringArray(R.array.episodes);
        String[] urls =  this.getApplicationContext().getResources().getStringArray(R.array.episode_url);
        for(int i = 0; i < strings_name.length; i++){
            if(strings_name[i].equals(name)){
                Log.i("TAG", urls[i]);
                return urls[i];
            }
        }
        return "www.google.com";
    }
}
