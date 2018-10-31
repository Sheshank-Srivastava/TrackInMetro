package android.example.com.trackinmetro.model;

public class LastTripModel {
    private String stationOne;
    private String stationTwo;

    public LastTripModel(String stationOne, String stationTwo) {
        this.stationOne = stationOne;
        this.stationTwo = stationTwo;
    }

    public String getStationOne() {
        return stationOne;
    }

    public void setStationOne(String stationOne) {
        this.stationOne = stationOne;
    }

    public String getStationTwo() {
        return stationTwo;
    }

    public void setStationTwo(String stationTwo) {
        this.stationTwo = stationTwo;
    }
}
