package android.example.com.trackinmetro.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.adapter.LastTripAdapter;
import android.example.com.trackinmetro.model.LastTripModel;
import android.example.com.trackinmetro.utilities.Constants;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imgSwapRoute;
    Animation animRotateView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    AutoCompleteTextView txtSource, txtDestination;
    Button butFindRoute;
    public static ArrayList<String> tempStationName;
    ArrayList<LastTripModel> mdata;
    int backPressedLook = Constants.EXIT_KEY;
    String sourceName, destination;
    RecyclerView recLastTrip;
    SharedPreferences sharedPreferences;
    ArrayList<String> stationName = SplashActivity.stationName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animRotateView = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_view);

        navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        txtSource = findViewById(R.id.txtSource);
        txtDestination = findViewById(R.id.txtDestination);
        butFindRoute = findViewById(R.id.butFindRoute);
        recLastTrip = findViewById(R.id.recRecentTrip);
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_DATA,MODE_PRIVATE);
        int sc =sharedPreferences.getInt(Constants.SOURCE_STATION_CODE,-1);
        int des =sharedPreferences.getInt(Constants.DESTINATION_STATION_CODE,-1);
        if (sc!=-1)
            txtSource.setText(stationName.get(sc));
        if (des!=-1)
            txtDestination  .setText(stationName.get(des));
        initComponent();

    }

    private void initComponent() {

        /**
         * ImageSwap View
         */
        imgSwapRoute = findViewById(R.id.imgSwapRoute);
        imgSwapRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String change = "";
                if (txtDestination.getText() != null && txtSource.getText() != null) {
                    imgSwapRoute.startAnimation(animRotateView);
                    change = txtSource.getText() + "";
                    txtSource.setText(txtDestination.getText() + "");
                    txtDestination.setText(change);
                }
            }
        });
        /**
         * Navigation Drawer
         */
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Set Trip");
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        /**
         * Edit Text With Filter
         */
        txtSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSource.setFocusableInTouchMode(true);
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, stationName);
        //Find TextView control

        //Set the number of characters the user must type before the drop down list is shown
        txtSource.setThreshold(1);
        //Set the adapter
        txtSource.setAdapter(adapter);
        txtDestination.setThreshold(1);
        //Set the adapter
        txtDestination.setAdapter(adapter);
        /**
         * Find Route Button Function
         */
        butFindRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean checkField = checkFieldsCorrectness();
                if (checkField) {
                    Intent intent = new Intent(MainActivity.this, LastTripsActivity.class);
                    int source = stationName.indexOf(txtSource.getText().toString().trim());
                    int destination = stationName.indexOf(txtDestination.getText().toString().trim());
//                    intent.putExtra("source", source);
//                    intent.putExtra("destination", destination);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putInt(Constants.SOURCE_STATION_CODE,source);
                    editor.putInt(Constants.DESTINATION_STATION_CODE,destination);
                    editor.apply();
                    Log.d("Station Code",source+"->"+destination);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Fields Data Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         * Recycler View
         */
        mdata = new ArrayList<>();
        mdata.add(new LastTripModel("Preet Vihar", "Pul Bangash"));
        mdata.add(new LastTripModel("Qutab Minar", "Rajdhani Park"));
        mdata.add(new LastTripModel("Ramesh Nagar", "Rohini West"));
        mdata.add(new LastTripModel("Satguru Ramsingh Marg", "Shadipur"));
        mdata.add(new LastTripModel("Preet Vihar", "Rajouri Garden"));
        recLastTrip.setHasFixedSize(true);
        recLastTrip.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recLastTrip.setAdapter(new LastTripAdapter(MainActivity.this, mdata));
    }

    /**
     * Check Weather Fields are with Correct Data or not
     *
     * @return
     */
    private boolean checkFieldsCorrectness() {
        sourceName = txtSource.getText().toString().trim();
        destination = txtDestination.getText().toString().trim();
        if (!(sourceName.isEmpty()) && !(destination.isEmpty())) {
            if (stationName.contains(sourceName) && stationName.contains(destination)) {
                Log.d("DataIsCorrect", "Data Found" + "// " + sourceName + "// " + destination);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            backPressedLook = Constants.EXIT_KEY;
        } else {
            backPressedLook++;
            if (backPressedLook == 1)
                Toast.makeText(this, "Press Again To Exit", Toast.LENGTH_SHORT).show();
            if (backPressedLook == 2)
                super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        backPressedLook = Constants.EXIT_KEY;
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.route_menu) {
            startActivity(new Intent(MainActivity.this, LastTripsActivity.class));
        } else if (id == R.id.fare_menu) {
            startActivity(new Intent(MainActivity.this, FareActivity.class));
        } else if (id == R.id.map_menu) {
            startActivity(new Intent(MainActivity.this, MapActivity.class));
        } else if (id == R.id.firstLast_menu) {
            startActivity(new Intent(MainActivity.this, FirstLastActivity.class));
        } else if (id == R.id.logout_menu) {

            finish();
        } else if (id == R.id.nav_share) {
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            // Add data to the intent, the receiving app will decide
            // what to do with it.
            share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
            share.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/");

            startActivity(Intent.createChooser(share, "Share App"));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



}
