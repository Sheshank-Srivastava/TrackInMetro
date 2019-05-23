package android.example.com.trackinmetro.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class UtilityFunctions {
    public static int getFare(int no) {
        int fare = 0;
        Log.d("Total Number", no + "----------");
        no--;
        if (no / 25 > 0) {
            fare = 60;
        } else if (no / 15 > 0) {
            fare = 50;
        } else if (no / 9 > 0) {
            fare = 40;
        } else if (no / 5 > 0) {
            fare = 30;
        } else if (no / 2 > 0) {
            fare = 20;
        } else {
            fare = 10;
        }
        return fare;
    }

}
