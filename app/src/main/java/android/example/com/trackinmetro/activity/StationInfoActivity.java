package android.example.com.trackinmetro.activity;

import android.example.com.trackinmetro.R;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StationInfoActivity extends AppCompatActivity {
ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);
        actionBar = getSupportActionBar();
        intiComponent();
    }

    private void intiComponent() {
        actionBar.setTitle("About Metro-Station");
    }
}
