package android.example.com.trackinmetro.activity;

import android.content.Intent;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.adapter.ListRouteAdapter;
import android.example.com.trackinmetro.model.RouteListModel;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class LastTripsActivity extends AppCompatActivity {
    ActionBar actionBar;
    String sourceName = "", destinationName = "", sourceColor = "", destinationColor = "";
    AutoCompleteTextView txtSource, txtDestination;
    ImageView butFind;
    int sourceCode = 0, destinationCode = 0;
    ArrayList<String> mdata, listOfCode;
    ArrayList<RouteListModel> stationData, listData;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_trips);
        /**
         * Intent Data
         */
        Log.d("Intent", "Befor Intent");
        Intent intent = getIntent();
        sourceName = intent.getStringExtra("source");
        destinationName = intent.getStringExtra("destination");
        Log.d("Intent", "After Intent" + sourceName + "==" + destinationName);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, mdata);
        //Find TextView control

        //Set the number of characters the user must type before the drop down list is shown
        txtSource.setThreshold(1);
        //Set the adapter
        txtSource.setAdapter(adapter);
        txtDestination.setThreshold(1);
        //Set the adapter
        txtDestination.setAdapter(adapter);
        dataRecycler();
        butFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillRecycler();
            }
        });
        if (sourceName != null && destinationName != null) {
            fillRecycler();
        }
    }

    public void fillRecycler() {
        listData.clear();
        Log.d("SourceName", "1");

        if (!txtSource.getText().equals("") && !txtDestination.getText().equals("")) {
            Log.d("SourceName", "2");

            for (int i = 0; i < stationData.size(); i++) {
                RouteListModel model = stationData.get(i);
                Log.d("SourceName", model.getStationName().replace(" ", "") + " hola " + i + sourceName+"==="+destinationName);

                if (model.getStationName().replace(" ", "").equals(sourceName)) {
//                    sourceCode = Integer.parseInt(stationData.get(i).getStationCode().replace("Y", ""));
                    if (model.getStationCode().size() == 1) {
                        sourceCode = Integer.parseInt(model.getStationCode().get(0).substring(1) + "");
                        sourceColor = model.getStationCode().get(0).charAt(0) + "";
                        Log.d("SourceName111", sourceCode + "==" + model.getStationName() + "///" + model.getStationCode().get(0).charAt(0));

                    }
                    if (model.getStationCode().size() > 1) {

                    }
                }

                if (model.getStationName().replace(" ", "").equals(destinationName)) {
                    if (model.getStationCode().size() == 1) {
                        destinationCode = Integer.parseInt(model.getStationCode().get(0).substring(1) + "");
                        Log.d("destinationName", destinationCode + "==" + model.getStationName() + "///" + model.getStationCode().get(0).charAt(0));
                        destinationColor = model.getStationCode().get(0).charAt(0) + "";

                    }
                    if (model.getStationCode().size() > 1) {
                            for(int j=0;j<model.getStationCode().size();j++){
                                if(!sourceColor.equals("")){
                                    if()
                                }
                            }
                    }
                }

            }
            if (sourceColor.equals(destinationColor)) {
                Log.d("GetTheCodeSocDis",sourceName+"//"+destinationName);

                for (int i = 0; i <stationData.size();i++){
                    RouteListModel model = stationData.get(i);
                    for(int j =0;j<model.getStationCode().size();j++){
                        String code = model.getStationCode().get(j).charAt(0)+"";
                        if(code.equals(sourceColor)){
                            Log.d("GetTheCode",i+"=="+code+"=="+model.getStationName()+"=="+model.getStationCode().get(j).replace(sourceColor,""));
//                            listData.add(model);
                        }
                    }
                }
            }

        }
        Log.d("StationAndDes", sourceCode + "==" + destinationCode);
//
//        if (sourceCode > destinationCode) {
//            Log.d("StationAndDes", "sourceCode>destinationCode");
//            Log.d("StationAndDes", sourceCode + "sourceCode>destinationCode");
//            Log.d("StationAndDes", destinationCode + "sourceCode>destinationCode");
//            for (int i = sourceCode - 1; i >= destinationCode - 1; i--) {
//                listData.add(stationData.get(i));
//            }
//        } else {
//            Log.d("StationAndDes", "sourceCode<destinationCode");
//            Log.d("StationAndDes", sourceCode + "sourceCode<destinationCode");
//            Log.d("StationAndDes", destinationCode + "sourceCode<destinationCode");
//            for (int i = sourceCode - 1; i <= destinationCode - 1; i++) {
//                Log.d("StationList", stationData.get(i + 1) + "====" + stationData.get(i + 1).getStationName());
//                listData.add(stationData.get(i));
//
//            }
//        }
//        for (int i = 0; i < listData.size(); i++) {
//            Log.d("newStationData", listData.get(i).getStationName() + "");
//        }
//        if (listData.size() > 0) {
//            RecyclerView recyclerView = findViewById(R.id.recRouteToShow);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(LastTripsActivity.this));
//            recyclerView.setAdapter(new ListRouteAdapter(LastTripsActivity.this, listData));
//
//        }
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
            /**
             * Filling Data for StationData List
             */
            Log.d("JsonData", jsonArray.length() + "");

            for (int i = 0; i < jsonArray.length(); i++) {
                /**
                 *  Data
                 */
                Log.d("JsonData", i + "");

                JSONObject arrayObject = jsonArray.getJSONObject(i);
                JSONObject detailObject = arrayObject.getJSONObject("details");
                JSONArray statNumberArray = detailObject.getJSONArray("stationNumber");
                String name = arrayObject.getString("name");
                ArrayList<String> statCodes = new ArrayList<>();
                for (int j = 0; j < statNumberArray.length(); j++) {
                    statCodes.add(statNumberArray.get(j) + "");
                    Log.d("StationCodeList", statNumberArray.get(j) + "--" + i);
                }
                stationData.add(new RouteListModel(name, statCodes));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class SortByName implements Comparator<RouteListModel> {
        @Override
        public int compare(RouteListModel routeListModel, RouteListModel t1) {
            return /*routeListModel.getStationCode().compareTo(t1.getStationCode())*/0;
        }
    }
}