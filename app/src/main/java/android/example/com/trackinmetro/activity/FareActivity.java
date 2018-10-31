package android.example.com.trackinmetro.activity;

import android.example.com.trackinmetro.R;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class FareActivity extends AppCompatActivity {
    ActionBar actionBar;
   ArrayList<String> mdata;
    AutoCompleteTextView txtSource, txtDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);
        mdata = new ArrayList<>(MainActivity.stationName);
        actionBar = getSupportActionBar();
        txtSource = findViewById(R.id.txtFareSource);
        txtDestination = findViewById(R.id.txtFareDestination);
        intiComponent();
    }

    private void intiComponent() {
        actionBar.setTitle("Fare");
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
