package android.example.com.trackinmetro.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.utilities.Constants;
import android.example.com.trackinmetro.utilities.UtilityFunctions;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.example.com.trackinmetro.utilities.UtilityFunctions.*;

public class FareActivity extends AppCompatActivity {
    ActionBar actionBar;
    ArrayList<String> mdata;
    AutoCompleteTextView txtSource, txtDestination;
    TextView txtFare,txtDuration,txtInterChange,txtTotalStation;
    SharedPreferences shared;
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
        shared = getSharedPreferences(Constants.SHARED_PREF_DATA, MODE_PRIVATE);

        txtSource.setText(SplashActivity.stationName.get(shared.getInt(Constants.SOURCE_STATION_CODE, -1)) + "");
        txtDestination.setText(SplashActivity.stationName.get(shared.getInt(Constants.DESTINATION_STATION_CODE, -1)) + "");
        int fare =UtilityFunctions.getFare(shared.getInt(Constants.TOTAL_STATION, 0));
        txtFare.setText(fare+"");
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt(Constants.TOTAL_FARE, fare);
        editor.apply();
        txtTotalStation.setText(""+shared.getInt(Constants.TOTAL_STATION,0));
        txtInterChange.setText(""+shared.getInt(Constants.INTERCHANGE_STATION,0));
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


}
