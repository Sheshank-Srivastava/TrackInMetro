package android.example.com.trackinmetro.activity;

import android.example.com.trackinmetro.R;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class FirstLastActivity extends AppCompatActivity {
    ActionBar actionBar;
    ArrayList<String> mdata;
    AutoCompleteTextView txtSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_last);
        actionBar = getSupportActionBar();
        txtSource = findViewById(R.id.txtFirstLast);
        mdata = new ArrayList<>(MainActivity.stationName);

        intiComponent();
    }

    private void intiComponent() {
        actionBar.setTitle("First And Last");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, mdata);
        //Find TextView control

        //Set the number of characters the user must type before the drop down list is shown
        txtSource.setThreshold(1);
        //Set the adapter
        txtSource.setAdapter(adapter);
    }
}
