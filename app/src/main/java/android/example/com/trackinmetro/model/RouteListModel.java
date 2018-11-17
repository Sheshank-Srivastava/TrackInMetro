package android.example.com.trackinmetro.model;

import java.util.ArrayList;

public class RouteListModel {
    private String stationName;
    private ArrayList<String> stationCode;

    public RouteListModel(String stationName,  ArrayList<String> stationCode) {
        this.stationName = stationName;
        this.stationCode = stationCode;
    }

    public ArrayList<String> getStationCode() {
        return stationCode;
    }

    public void setStationCode(ArrayList<String> stationCode) {
        this.stationCode = stationCode;
    }


    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

  }
