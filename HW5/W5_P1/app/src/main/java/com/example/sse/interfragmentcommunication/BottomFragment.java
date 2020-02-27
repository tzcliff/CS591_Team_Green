package com.example.sse.interfragmentcommunication;


import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//this will get inflated down below.

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {

    private TextView txtFunnyMessage;

    public BottomFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);  //separate me from return statement.
        txtFunnyMessage = (TextView)view.findViewById(R.id.txtFunnyMessage);      //need a chance to do this other stuff
        return view;

    }



    //Receiving Team
    //It is best practice that this should be accessed via the main activity, not other fragments.
    public void setFood(Food food) {
        switch (food) {
            case CHEESEBURGER:
                txtFunnyMessage.setText("Cheese Burger");
                this.getView().setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.cheeseburger));
                break;
            case HOTDOG:
                txtFunnyMessage.setText("Hot Dog");
                this.getView().setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.hotdog));
                break;
            case PIZZA:
                txtFunnyMessage.setText("Pizza");
                this.getView().setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.pizza));
                break;
            case SPAGHETTI:
                txtFunnyMessage.setText("Spaghetti");
                this.getView().setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.spaghetti));
                break;
            case STIRFRY:
                txtFunnyMessage.setText("Stir Fry");
                this.getView().setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.stirfry));
                break;
            default:
                break;
        }
    }

}
