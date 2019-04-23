package android.example.com.trackinmetro.MapGraph;

public class GraphTime {

    private int source;
    private int destination;
    private int duration;


    public GraphTime(int source, int destination, int duration) {
        this.source = source;
        this.destination = destination;
        this.duration = duration;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
