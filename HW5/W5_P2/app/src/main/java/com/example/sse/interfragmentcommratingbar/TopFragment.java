package com.example.sse.interfragmentcommratingbar;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TopFragment extends Fragment {


    private Button btnLeft;
    private Button btnRight;
    private ControlFragmentListener CFL;

    public TopFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CFL = (ControlFragmentListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CFL = (ControlFragmentListener) activity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_top, container, false);
        btnRight = (Button) view.findViewById(R.id.btnRight);
        btnLeft = (Button) view.findViewById(R.id.btnLeft);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CFL.sendDirection("left");
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CFL.sendDirection("right");
            }
        });

        return view;
    }

    interface ControlFragmentListener{
        void sendDirection(String direction);
    }
}

