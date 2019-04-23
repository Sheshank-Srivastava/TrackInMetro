package android.example.com.trackinmetro.model;

import java.util.ArrayList;

public class RouteListModel {
    private String stationName;
    private int stationNumber;
    private ArrayList<String> stationColorlist;
    private ArrayList<String> gateDirlist;

    public RouteListModel(String stationName, int stationNumber, ArrayList<String> stationColorlist, ArrayList<String> gateDirlist) {
        this.stationName = stationName;
        this.stationNumber = stationNumber;
        this.stationColorlist = stationColorlist;
        this.gateDirlist = gateDirlist;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public ArrayList<String> getStationColorlist() {
        return stationColorlist;
    }

    public void setStationColorlist(ArrayList<String> stationColorlist) {
        this.stationColorlist = stationColorlist;
    }

    public ArrayList<String> getGateDirlist() {
        return gateDirlist;
    }

    public void setGateDirlist(ArrayList<String> gateDirlist) {
        this.gateDirlist = gateDirlist;
    }
}
