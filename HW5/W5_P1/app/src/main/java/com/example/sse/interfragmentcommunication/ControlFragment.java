package com.example.sse.interfragmentcommunication;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */

//This will get inflated up top.
public class ControlFragment extends Fragment {


    private ListView listView;

    public ControlFragment() {  //todo, why?
        // Required empty public constructor
    }

//*** MESSAGE PASSING MECHANISM ***//
//Need to create an interface to ensure message passing works between fragments.
//This interface, as with all interfaces serves as a contract.  Implementer of this interface, must implement all of its methods.
//Important Fact: Since the MainActivity will implement this, we are guaranteed to find a sendMessage
//routine there!
    public interface ControlFragmentListener {            //this is just an interface definition.
        public void sendFood(Food food); //it could live in its own file.  placed here for convenience.
    }

    ControlFragmentListener CFL;  //Future reference to an object that implements ControlFragmentListener, Can be anything, as long as it implements all interface methods.
                                  //Question: Who holds the reference?  Answer: ____________
//*** MESSAGE PASSING MECHANISM ***//


    //onAttach gets called when fragment attaches to Main Activity.  This is the right time to instantiate
    //our ControlFragmentListener, why?  Because we know the Main Activity was successfully created and hooked.
    @Override
    public void onAttach(Context context) {   //The onAttach method, binds the fragment to the owner.  Fragments are hosted by Activities, therefore, context refers to: ____________?
        super.onAttach(context);
        CFL = (ControlFragmentListener) context;  //context is a handle to the main activity, let's bind it to our interface.
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

        View view = inflater.inflate(R.layout.fragment_control, container, false);  //this needs to be separated from return statement,

        listView = (ListView) view.findViewById(R.id.foodListView);

//2. Create an Adapter to bind to your ListView.
        final String[] foodList = {"Hot Dog", "Cheese Burger", "Stir Fry", "Spaghetti", "Pizza"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TAG", String.valueOf(position));
                switch (position) {
                    case 0:
                        CFL.sendFood(Food.HOTDOG);
                        break;
                    case 1:
                        CFL.sendFood(Food.CHEESEBURGER);
                        break;
                    case 2:
                        CFL.sendFood(Food.STIRFRY);
                        break;
                    case 3:
                        CFL.sendFood(Food.SPAGHETTI);
                        break;
                    case 4:
                        CFL.sendFood(Food.PIZZA);
                        break;
                    default:
                        break;
                }

            }
        });


        return view;
    }

}
