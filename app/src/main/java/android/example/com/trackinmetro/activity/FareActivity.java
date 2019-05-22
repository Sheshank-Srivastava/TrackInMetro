package android.example.com.trackinmetro.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.utilities.Constants;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class FareActivity extends AppCompatActivity {
    ActionBar actionBar;
    ArrayList<String> mdata;
    AutoCompleteTextView txtSource, txtDestination;
    TextView txtFare,txtDuration,txtInterChange,txtTotalStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);
        mdata = new ArrayList<>(SplashActivity.stationName);
        actionBar = getSupportActionBar();
        txtSource = findViewById(R.id.txtFareSource);
        txtDestination = findViewById(R.id.txtFareDestination);
        txtFare = findViewById(R.id.txtCostOfTravel);
        txtDuration= findViewById(R.id.txtTimeOfTravel);
        txtInterChange = findViewById(R.id.txtInterChangeLine);
        txtTotalStation = findViewById(R.id.txtNoStation);
        intiComponent();
    }

    @SuppressLint("SetTextI18n")
    private void intiComponent() {
        actionBar.setTitle("Fare");
        SharedPreferences shared = getSharedPreferences(Constants.SHARED_PREF_DATA, MODE_PRIVATE);
        txtSource.setText(SplashActivity.stationName.get(shared.getInt(Constants.SOURCE_STATION_CODE, -1)) + "");
        txtDestination.setText(SplashActivity.stationName.get(shared.getInt(Constants.DESTINATION_STATION_CODE, -1)) + "");
        getFare();
        txtTotalStation.setText(""+shared.getInt(Constants.TOTAL_STATION,0));
        txtInterChange.setText("1");
        txtDuration.setText(""+shared.getInt(Constants.TRAVELLING_TIME,0));

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

    private void getFare() {
    }
}
