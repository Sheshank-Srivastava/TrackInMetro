package android.example.com.trackinmetro.model;

public class StationName {
    private String name;
    private String layout;
    private String longitude;
    private String latitude;

    public StationName(String name, String layout, String longitude, String latitude) {
        this.name = name;
        this.layout = layout;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}