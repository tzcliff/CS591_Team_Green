package com.example.sse.fragmenttransactions_sse;

//import android.app.Activity;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{

//Step-By-Step, Fragment Transactions

    //Two basic ways of working with fragments.
    //
    //1. Just include them in the Activity's layout.
    //
    //2. Instantiate and work with them in code.
    // in code you have much more control.

    //3. create objects to reference the views, including fragments.
private
    Frag_One  f1;
    Frag_Two  f2;
    Frag_Three  f3;

    FragmentManager fm;  // we will need this later.

    private Button btnFrag1;
    private Button btnFrag2;
    private Button btnFrag3;
    private LinearLayout FragLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
        The original code had a few problems. It was using multiple different methods of Fragment Transactions so there wasn't any unification. I chose to use detach and attach because it does not destroy the Fragments,
        it only removes them from the View. This is less taxing on the hardware than having to create a whole new Fragment each time a button is pressed via the replace, or hide and add methods.
        I also changed the code around so that we always make sure everything isn't null before it is accessed. This will prevent any crashes from occuring. We always make sure to add to back stack so
        that the back button will work as the user anticipates.
         */


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //4. get references for our views.

        btnFrag1 = (Button) findViewById(R.id.btnFrag1);
        btnFrag2 = (Button) findViewById(R.id.btnFrag2);
        btnFrag3 = (Button) findViewById(R.id.btnFrag3);
        FragLayout = (LinearLayout) findViewById(R.id.FragLayout);

//        f1 = (Frag_One) findViewById(R.id.frag1);  //Q: Why won't this work for fragments?  Does the fragment even exist in R.java? A: No, because the fragments are hosted by the activity

    //5a.  We actually have to create the fragments ourselves.  We left R behind when we took control of rendering.
        f1 = new Frag_One();
        f2 = new Frag_Two();
        f3 = new Frag_Three();

    //5b. Grab a reference to the Activity's Fragment Manager, Every Activity has one!
       fm = getFragmentManager();  //that was easy.
//         fm = getSupportFragmentManager();  // **When would you use this instead?? A: When you are using android.support.v4.app.FragmentManager then you should use getSupportFragmentManager() and if you are using android.app.FragmentManager then use getFragmentManager()


    //5c. Now we can "plop" fragment(s) into our container.
        FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.
        ft.add(R.id.FragLayout, f1, "tag1");  //now we have added our fragement to our Activity programmatically.  The other fragments exist, but have not been added yet.
        ft.addToBackStack ("myFrag1");  //why do we do this? A: So the back button works as the user expects
        ft.commit ();  //don't forget to commit your changes.  It is a transaction after all.


    btnFrag1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showFrag1();
        }
    });

        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrag2();
            }
        });

        btnFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrag3();
            }
        });

    }

public void showFrag1() {

     if (f1 == null) // make sure f1 still exists
         f1 = new Frag_One();


    FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.

    if (fm.findFragmentByTag("tag1") == null) { // check if the fragment exists

        ft.add(R.id.FragLayout, f1, "tag1"); // if not, we must add it again with the tag
    }

    if (fm.findFragmentByTag("tag1") != null) { // make sure it exists
        f1 = (Frag_One) fm.findFragmentByTag("tag1");   //what should we do if f1 doesn't exist anymore?  How do we check and how do we fix? A: If it no longer exists, create a new instance of it. Check if it's null via an if statement.

    }
    // ft.replace(R.id.FragLayout, f1); we won't be using replace because it is more resource intensive

    if (f2 != null) { // make sure f2 exists
        ft.detach(f2);
    }

    if (f3 != null) {
        ft.detach(f3);
    }

    ft.attach(f1);
    ft.addToBackStack("myFrag1");


//    ft.hide(f2);
//    ft.hide(f3);
//    ft.show(f1);   //why does this not *always* crash? A: Because f1 always exists and is shown during onCreate
    ft.commit();
}

    public void showFrag2() {

        if (f2 == null)
          f2 = new Frag_Two();

        FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction and start the transaction.

        if (fm.findFragmentByTag("tag2") == null) {

            ft.add(R.id.FragLayout, f2, "tag2");
        }

        if (fm.findFragmentByTag("tag2") != null) {
            f2 = (Frag_Two) fm.findFragmentByTag("tag2");
        }
            // ft.replace(R.id.FragLayout, f2);

            if (f1 != null) {
                ft.detach(f1);
            }

            if (f3 != null) {
                ft.detach(f3);
            }

            ft.attach(f2);
            ft.addToBackStack ("myFrag2");  //Q: What is the back stack and why do we do this? A: The back stack keeps track of previous Activities so that the back button works as the user expects it to
            ft.commit();

    }


    public void showFrag3() {

        if (f3 == null)
            f3 = new Frag_Three();




        FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.

        if (fm.findFragmentByTag("tag3") == null) {
            ft.add(R.id.FragLayout, f3, "tag3");
        }

        if (fm.findFragmentByTag("tag3") != null) {
            f3 = (Frag_Three) fm.findFragmentByTag("tag3");
        }



            // ft.replace(R.id.FragLayout, f3);

            if (f1 != null) {
                ft.detach(f1);
            }

            if (f2 != null) {
                ft.detach(f2);
            }

            ft.attach(f3);
            ft.addToBackStack("myFrag3");


//        ft.detach(f1);   //what would happen if f1, f2, or f3 were null?  how would we check and fix this? A: It would crash. Since they are all added in onCreate, they will always exist.
//        ft.detach(f2);
//        ft.attach(f3);
            ft.commit();


    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }
}
