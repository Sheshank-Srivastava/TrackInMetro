package android.example.com.trackinmetro.activity;

import android.content.Intent;
import android.example.com.trackinmetro.R;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class LastTripsActivity extends AppCompatActivity {
    ActionBar actionBar;
    String sourceName,destinationName;
    AutoCompleteTextView txtSource,txtDestination;
    ArrayList<String> mdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_trips);
        /**
         * Intent Data
         */
        Intent intent = getIntent();
        sourceName = intent.getStringExtra("source");
        destinationName = intent.getStringExtra("destination");
        /**
         * Component
         */
        actionBar = getSupportActionBar();
        txtSource = findViewById(R.id.txtSource);
        txtDestination = findViewById(R.id.txtDestination);
        mdata = new ArrayList<>(MainActivity.stationName);

//        Log.d("FromMainActivity",sourceName+"{}"+destination);
        intiComponent();
    }

    private void intiComponent() {
        actionBar.setTitle("Last Trips");
        txtSource.setText(sourceName);
        txtDestination.setText(destinationName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, mdata);
        //Find TextView control

        //Set the number of characters the user must type before the drop down list is shown
        txtSource.setThreshold(1);
        //Set the adapter
        txtSource.setAdapter(adapter);
        txtDestination.setThreshold(1);
        //Set the adapter
        txtDestination.setAdapter(adapter);
    }
}
