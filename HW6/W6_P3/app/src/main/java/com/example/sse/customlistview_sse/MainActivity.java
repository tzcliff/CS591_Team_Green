package com.example.sse.customlistview_sse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

//Step-By-Step, Setting up the ListView

    private
    ListView lvEpisodes;     //Reference to the listview GUI component
    MyCustomAdapter lvAdapter;   //Reference to the Adapter used to populate the listview.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEpisodes = (ListView)findViewById(R.id.lvEpisodes);
        lvAdapter = new MyCustomAdapter(this.getBaseContext());  //instead of passing the boring default string adapter, let's pass our own, see class MyCustomAdapter below!

        lvEpisodes.setAdapter(lvAdapter);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);   //get rid of default behavior.

        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.my_test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mnu_zero) {
            Toast.makeText(getBaseContext(), "Menu Zero.", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.mnu_one) {
            Toast.makeText(getBaseContext(), "Ring ring, Hi Mom.", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.mnu_three) {
             Toast.makeText(getBaseContext(), "Hangup it's a telemarketer.", Toast.LENGTH_LONG).show();
            return true;
        }

        /**
         *  if the menu item is sort by title
         */
        if(id == R.id.menu_sortByTitle){

            Log.i("TAG", "Sort By title clicked");

            return true;
        }
            return super.onOptionsItemSelected(item);  //if none of the above are true, do the default and return a boolean.
    }
}



//STEP 1: Create references to needed resources for the ListView Object.  String Arrays, Images, etc.

class MyCustomAdapter extends BaseAdapter {


    // Use a list of Episode objects which concludes all the info.
    private List<Episode> episodes;
    // Add SharedPreferences variable
    private SharedPreferences sharedPreferences;
    // Add RatingBar variable

    RatingBar ratingBar;
    Button btnRandom;
    Context context;   //Creating a reference to our context object, so we only have to get it once.  Context enables access to application specific resources.
    // Eg, spawning & receiving intents, locating the various managers.


    public MyCustomAdapter(Context aContext) {
    //initializing our data in the constructor.
        context = aContext;  //saving the context we'll need it again.
        // Initialize ArrayList
        episodes = new ArrayList<>();

        List<Integer> episodeImages;
        episodeImages = new ArrayList<>();
        episodeImages.add(R.drawable.st_spocks_brain);
        episodeImages.add(R.drawable.st_arena__kirk_gorn);
        episodeImages.add(R.drawable.st_this_side_of_paradise__spock_in_love);
        episodeImages.add(R.drawable.st_mirror_mirror__evil_spock_and_good_kirk);
        episodeImages.add(R.drawable.st_platos_stepchildren__kirk_spock);
        episodeImages.add(R.drawable.st_the_naked_time__sulu_sword);
        episodeImages.add(R.drawable.st_the_trouble_with_tribbles__kirk_tribbles);
        // Get values
        for(int i = 0; i < episodeImages.size(); i++){
            String name = aContext.getResources().getStringArray(R.array.episodes)[i];
            String descriptions = aContext.getResources().getStringArray(R.array.episode_descriptions)[i];
            Float ratings = 0.0F;
            episodes.add(new Episode(episodeImages.get(i), name, descriptions, ratings));
        }
        // Retrieve data from SharedPreferences
        retrieveRateBarSharedPreferenceInfo();
    }


    //Notifies the attached observers that the underlying data has been changed and any View reflecting the data set should refresh itself.
    @Override
    public void notifyDataSetChanged() {

        super.notifyDataSetChanged();
    }

    //STEP 3: Override and implement getCount(..), ListView uses this to determine how many rows to render.
    @Override
    public int getCount() {
        return episodes.size();
    }

    //STEP 4: Override getItem/getItemId, we aren't using these, but we must override anyway.
    @Override
    public Object getItem(int position) {
        return episodes.get(position).getEpisodeName();
    }

    @Override
    public long getItemId(int position) {
        return position;  //Another call we aren't using, but have to do something since we had to implement (base is abstract).
    }


    /**
     * Save rating bar info
     *
     * @param episodeName
     * @param rating
     */
    void saveRateBarSharedPreferenceInfo(String episodeName, Float rating) {

        sharedPreferences = context.getSharedPreferences("Info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Add rate bar info
        editor.putFloat(episodeName, rating);
        //4. Save your information.
        editor.apply();

    }

    /**
     * Retrieve all ratings
     */
    void retrieveRateBarSharedPreferenceInfo() {
        sharedPreferences = context.getSharedPreferences("Info", Context.MODE_PRIVATE);
        for (int i = 0; i < episodes.size(); i++) {
            episodes.get(i).setEpisodeRatings(sharedPreferences.getFloat(episodes.get(i).getEpisodeName() + "_rb", 0.0F));;
        }
    }


    //THIS IS WHERE THE ACTION HAPPENS.  getView(..) is how each row gets rendered.
//STEP 5: Easy as A-B-C
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {  //convertView is Row (it may be null), parent is the layout that has the row Views.

//STEP 5a: Inflate the listview row based on the xml.

        View row;  //this will refer to the row to be inflated or displayed if it's already been displayed. (listview_row.xml)
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        row = inflater.inflate(R.layout.listview_row, parent, false);  //

// Let's optimize a bit by checking to see if we need to inflate, or if it's already been inflated...
        if (convertView == null) {  //indicates this is the first time we are creating this row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //Inflater's are awesome, they convert xml to Java Objects!
            row = inflater.inflate(R.layout.listview_row, parent, false);
        } else {
            row = convertView;
        }

        // a. Set row to be clickable to trigger the onClick Event
        row.setClickable(true);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DisplayWebActivity.class);
                intent.putExtra("Name", episodes.get(position).getEpisodeName());
                view.getContext().startActivity(intent);
            }
        });


//STEP 5b: Now that we have a valid row instance, we need to get references to the views within that row and fill with the appropriate text and images.
        ImageView imgEpisode = (ImageView) row.findViewById(R.id.imgEpisode);  //Q: Notice we prefixed findViewByID with row, why?  A: Row, is the container.
        TextView tvEpisodeTitle = (TextView) row.findViewById(R.id.tvEpisodeTitle);
        TextView tvEpisodeDescription = (TextView) row.findViewById(R.id.tvEpisodeDescription);


        // Find ratingBar by Id
        ratingBar = (RatingBar) row.findViewById(R.id.rbEpisode);


        // When change rating, change the saved value as well.

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                episodes.get(position).setEpisodeRatings(v);
                saveRateBarSharedPreferenceInfo(episodes.get(position).getEpisodeName() + "_rb", v);
            }
        });

        // set rating bar rating to saved value.
        ratingBar.setRating(episodes.get(position).getEpisodeRatings());
        tvEpisodeTitle.setText(episodes.get(position).getEpisodeName());
        tvEpisodeDescription.setText(episodes.get(position).getEpisodeDescriptions());
        imgEpisode.setImageResource(episodes.get(position).getEpisodeImage().intValue());

        btnRandom = (Button) row.findViewById(R.id.btnRandom);
        final String randomMsg = ((Integer) position).toString() + ": " + episodes.get(position).getEpisodeDescriptions();
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, randomMsg, Toast.LENGTH_LONG).show();
            }
        });

        return row;


    }



}