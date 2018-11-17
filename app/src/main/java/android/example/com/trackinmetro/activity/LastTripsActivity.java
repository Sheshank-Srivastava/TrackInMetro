package android.example.com.trackinmetro.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.adapter.ListRouteAdapter;
import android.example.com.trackinmetro.model.RouteListModel;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class LastTripsActivity extends AppCompatActivity {
    ActionBar actionBar;
    String sourceName = "", destinationName = "";
    AutoCompleteTextView txtSource, txtDestination;
    ImageView butFind;
    int sourceCode = 0, destinationCode = 0;
    ArrayList<String> mdata;
    ArrayList<RouteListModel> stationData, listData;
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
        stationData = new ArrayList<>();
        listData = new ArrayList<>();
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
        dataRecycler();

// -----------------------------------------------------------------------------------------------------------------------------------------
//        if (!sourceName.equals("") && !destinationName.equals("")) {
        fillRecycler();
//        }
    }

    public void fillRecycler() {
        listData.clear();

        if (!txtSource.getText().equals("") && !txtDestination.getText().equals("")) {

            for (int i = 0; i < stationData.size(); i++) {
                if (stationData.get(i).getStationName().replace(" ", "").equals(sourceName)) {
                    sourceCode = Integer.parseInt(stationData.get(i).getStationCode().replace("Y", ""));
                    Log.d("SourceName", sourceCode + "");
                }

                if (stationData.get(i).getStationName().replace(" ", "").equals(destinationName)) {
                    destinationCode = Integer.parseInt(stationData.get(i).getStationCode().replace("Y", ""));
                    Log.d("destinationName", destinationCode + "");

                }
            }
        }
        Log.d("StationAndDes", sourceCode + "==" + destinationCode);

        if (sourceCode > destinationCode) {
            Log.d("StationAndDes", "sourceCode>destinationCode");
            Log.d("StationAndDes", sourceCode + "sourceCode>destinationCode");
            Log.d("StationAndDes", destinationCode + "sourceCode>destinationCode");
            for (int i = sourceCode - 1; i >= destinationCode - 1; i--) {
                listData.add(stationData.get(i));
            }
        } else {
            Log.d("StationAndDes", "sourceCode<destinationCode");
            Log.d("StationAndDes", sourceCode + "sourceCode<destinationCode");
            Log.d("StationAndDes", destinationCode + "sourceCode<destinationCode");
            for (int i = sourceCode - 1; i <= destinationCode - 1; i++) {
                Log.d("StationList", stationData.get(i + 1) + "====" + stationData.get(i + 1).getStationName());
                listData.add(stationData.get(i));

            }
        }
        for (int i = 0; i < listData.size(); i++) {
            Log.d("newStationData", listData.get(i).getStationName() + "");
        }
        RecyclerView recyclerView = findViewById(R.id.recRouteToShow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(LastTripsActivity.this));
        recyclerView.setAdapter(new ListRouteAdapter(LastTripsActivity.this, listData));
    }

    public void openDelay() {
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
        final Handler handler = new Handler();
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

    public void dataRecycler() {
        String json = "";
        try {
            InputStream is = getAssets().open("metroall.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            Log.d("JsonData", json + "");
            JSONObject firstObject = new JSONObject(json);
            JSONArray jsonArray = firstObject.getJSONArray("stationLine");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONObject no = obj.getJSONObject("details");
                String sName = obj.getString("name") + "";
                String sCode = "";
                JSONArray stationCode = no.getJSONArray("stationNumber");
               Log.d("StationCode",stationCode.length()+"hello");
                if(stationCode.length()>0) {
                   Log.d("JsonData", stationCode.getString(0).charAt(0) +" Data Here" + jsonArray.length());
               }
                if ((stationCode.getString(0).charAt(0)+"") != "Y") {
//                    sCode = no.getString("stationNumber") + "";
                    sCode = stationCode.getString(0)+"";
                    stationData.add(new RouteListModel(sName, sCode));
                    Log.d("StationData", sName + "-" + sCode);
                }

            }
            Collections.sort(stationData, new SortByName());
            for (int i = 0; i < stationData.size(); i++) {
                Log.d("AfterStationData", stationData.get(i).getStationName() + "-" + stationData.get(i).getStationCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//    public void sortListOFMetro(){
//
//    }

    public class SortByName implements Comparator<RouteListModel> {
        @Override
        public int compare(RouteListModel routeListModel, RouteListModel t1) {
            return routeListModel.getStationCode().compareTo(t1.getStationCode());
        }
    }
}