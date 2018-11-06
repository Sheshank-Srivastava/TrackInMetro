package android.example.com.trackinmetro.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.example.com.trackinmetro.R;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import java.util.ArrayList;

public class LastTripsActivity extends AppCompatActivity {
    ActionBar actionBar;
    String sourceName,destinationName;
    AutoCompleteTextView txtSource,txtDestination;
    ImageView butFind;
    ArrayList<String> mdata;
    RecyclerView recyclerView;

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
        recyclerView = findViewById(R.id.recRouteToShow);
        butFind = findViewById(R.id.imgSwapRoute);
        mdata = new ArrayList<>(MainActivity.stationName);

//        Log.d("FromMainActivity",sourceName+"{}"+destination);
        intiComponent();
    }

    private void intiComponent() {
        actionBar.setTitle("Last Trips");
        txtSource.setText(sourceName);
        txtDestination.setText(destinationName);
        openDelay();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, mdata);
        //Find TextView control

        //Set the number of characters the user must type before the drop down list is shown
        txtSource.setThreshold(1);
        //Set the adapter
        txtSource.setAdapter(adapter);
        txtDestination.setThreshold(1);
        //Set the adapter
        txtDestination.setAdapter(adapter);

        butFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void openDelay(){


        final AlertDialog.Builder dialog = new AlertDialog.Builder(this).setTitle("Leaving launcher").setMessage("Are you sure you want to leave the launcher?");
        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
//                exitLauncher();
            }
        });
        final AlertDialog alert = dialog.create();
        alert.show();

// Hide after some seconds
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 10000);


    }
    public void loadRecycler(){

    }
}
