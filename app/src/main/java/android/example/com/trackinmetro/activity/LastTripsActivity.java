package android.example.com.trackinmetro.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.example.com.trackinmetro.MapGraph.GraphMap;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.adapter.GateAdapter;
import android.example.com.trackinmetro.adapter.ListRouteAdapter;
import android.example.com.trackinmetro.model.RouteListModel;
import android.example.com.trackinmetro.utilities.Constants;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LastTripsActivity extends AppCompatActivity {
    ActionBar actionBar;
    ImageView butFind;
    AutoCompleteTextView txtSource, txtDestination;
    RecyclerView recyclerView;

    public static String sourceName = "", destinationName = "";
    int sourceCode = 0, destinationCode = 0;
    ArrayList<String> mdata;

    GraphMap map;
    ArrayList<Integer> list;
    ArrayList<RouteListModel> fullRouteData, listData;
    ListRouteAdapter adapter;

    public static int totalStation = 0;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_trips);

        /*Source*
         * Sharedpreference Data
         */
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_DATA, MODE_PRIVATE);
        sourceCode = sharedPreferences.getInt(Constants.SOURCE_STATION_CODE, -1);
        destinationCode = sharedPreferences.getInt(Constants.DESTINATION_STATION_CODE, -1);
        /**
         * Intent Data
         */
        Log.d("Intent", "Befor Intent");
//        Intent intent = getIntent();
//        sourceCode = intent.getIntExtra("source", -1);
//        destinationCode = intent.getIntExtra("destination", -1);
        Log.d("Intent", "After Intent" + sourceCode + "==" + destinationCode);

        /**
         * Component
         */
        actionBar = getSupportActionBar();
        txtSource = findViewById(R.id.txtSource);
        txtDestination = findViewById(R.id.txtDestination);
        butFind = findViewById(R.id.imgSwapRoute);
        mdata = new ArrayList<>(SplashActivity.stationName);
        /**
         * Recycler Data
         */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intiComponent();

            }
        }, 1000);
    }

    private void intiComponent() {
        actionBar.setTitle(mdata.get(sourceCode) + "->" + mdata.get(destinationCode));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, mdata);
        //Find TextView control

        //Set the number of characters the user must type before the drop down list is shown
        txtSource.setThreshold(1);
        //Set the adapter
        txtSource.setAdapter(adapter);
        txtDestination.setThreshold(1);
        //Set the adapter
        txtDestination.setAdapter(adapter);

        /**
         * Getting Route List
         */
        if (sourceCode != -1 && destinationCode != -1) {
            txtSource.setText(mdata.get(sourceCode));
            txtDestination.setText(mdata.get(destinationCode));

            dataRecycler();
            getData();
            setStationColor();
            for (int i = 0; i < fullRouteData.size(); i++) {
                Log.d("ListPrint", fullRouteData.get(i).getStationName());
            }

            adapter.notifyDataSetChanged();
//            sourceName = fullRouteData.get(0).getStationName().trim();
//            destinationName = fullRouteData.get(list.size() - 1).getStationName().trim();

        }
    }

    private void setStationColor() {
        String tempColor = "";
        int interChange = 0;
        for (int position = 0; position < fullRouteData.size(); position++) {

            if (fullRouteData.get(position).getStationColorlist().size() == 1) {
                tempColor = null;
                tempColor = fullRouteData.get(position).getStationColorlist().get(0);
            } else {

                if (position == (fullRouteData.size() - 1)) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(tempColor);
                    fullRouteData.get(position).setStationColorlist(list);
                } else {
                    RouteListModel tempModelmData = fullRouteData.get(position+1);
                    ArrayList<String> tempList = new ArrayList<>(fullRouteData.get(position).getStationColorlist());
                    tempList.retainAll(tempModelmData.getStationColorlist());
                    Log.d("GivenColor", tempList.toString() + "->" + fullRouteData.get(position).getStationName());
                    fullRouteData.get(position).setStationColorlist(tempList);
                    tempColor = tempList.get(0);

                }

            }
            if(position!=0 && !fullRouteData.get(position).getStationColorlist().get(0).equals(fullRouteData.get(position-1).getStationColorlist().get(0))){
                interChange+=1;
            }
        }
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_DATA,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.INTERCHANGE_STATION,interChange);
        editor.apply();
    }

    private void dataRecycler() {
        map = null;
        map = new GraphMap();
        Log.d("Intent", "After Intent" + sourceCode + "==" + destinationCode);
        list = map.getMap(this, sourceCode, destinationCode);
        Log.d("StationName", list.toString());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.TOTAL_STATION, list.size());
        editor.apply();
        recyclerView = findViewById(R.id.recRouteToShow);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        fullRouteData = new ArrayList<>();
        adapter = new ListRouteAdapter(this, fullRouteData, new ListRouteAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                final Dialog dialog = new Dialog(LastTripsActivity.this);
                dialog.setContentView(R.layout.dialog_gate_info);
                RecyclerView recyclerView = dialog.findViewById(R.id.rec_gateList);
                LinearLayoutManager gateManager = new LinearLayoutManager(LastTripsActivity.this);
                Log.d("gateList",fullRouteData.get(position).getGateDirlist().toString()+"");
                GateAdapter adapter = new GateAdapter(LastTripsActivity.this,fullRouteData.get(position).getGateDirlist());
                recyclerView.setLayoutManager(gateManager);
                recyclerView.setAdapter(adapter);
                /**
                 * Dialog ButtonFunction
                 */
                Button but_dialog = dialog.findViewById(R.id.but_dialogClose);
                but_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        totalStation = list.size();


    }

    public void getData() {
        String json = "";
        try {
            InputStream is = getAssets().open("metromapdata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            fullRouteData.clear();
            json = new String(buffer, "UTF-8");
            json = json.replace("\n", "");
//            json = json.replace(" ", "");
            JSONObject firstObject = new JSONObject(json);
            JSONArray jsonArray = firstObject.getJSONArray("data");
            for (int i = 0; i < list.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(list.get(i));
                int stationNumber = obj.getInt("id");
                String stationname = obj.getString("name");
                ArrayList<String> colorList = new ArrayList<>();
                JSONArray list = obj.getJSONArray("color");
                for (int j = 0; j < list.length(); j++) {
                    colorList.add(list.get(j) + "");
                }
                ArrayList<String> gateDirlist = new ArrayList<>();
                list = obj.getJSONArray("list");

                for (int j = 0; j < list.length(); j++) {
                    gateDirlist.add(list.get(j) + "");

                }
                fullRouteData.add(new RouteListModel(stationname, stationNumber, colorList, gateDirlist));

            }
//            Log.d("JsonData", stationName.size()+json + "======================");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        list = null;
        fullRouteData = null;
        map = null;
        super.onBackPressed();

//        Log.d("JsonData", "Both null"+list.toString());
    }
}