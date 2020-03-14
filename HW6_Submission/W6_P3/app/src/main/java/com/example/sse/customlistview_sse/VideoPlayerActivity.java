package com.example.sse.customlistview_sse;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.startrek;


        Uri uri = Uri.parse(videoPath);
        final VideoView videoView = (VideoView)findViewById(R.id.videoView);

        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();



    }
}
