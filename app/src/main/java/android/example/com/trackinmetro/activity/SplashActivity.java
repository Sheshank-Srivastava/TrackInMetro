package android.example.com.trackinmetro.activity;

import android.content.Intent;
import android.example.com.trackinmetro.R;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class SplashActivity extends AppCompatActivity {
    public static int splash_once = 0;
    public static ArrayList<String> stationName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation animImgSplash,animTxtSplash;
        animImgSplash = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_view);
        animTxtSplash = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_view);
        findViewById(R.id.imgSplashImage).startAnimation(animImgSplash);
        findViewById(R.id.txtSplashText).startAnimation(animTxtSplash);
        /**
         * Transfering view From Splash to MainActivity
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 * getList Of Station
                 */
                getData();

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                splash_once = 1;
            }
        },1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(splash_once == 1)
            finish();
    }
    /**
     * Function For Parsing the Json File
     */

    public void getData() {
        String json = "";
        try {
            InputStream is = getAssets().open("metromapdata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            stationName.clear();
            json = new String(buffer, "UTF-8");
            json = json.replace("\n", "");
//            json = json.replace(" ", "");
            JSONObject firstObject = new JSONObject(json);
            JSONArray jsonArray = firstObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                stationName.add(obj.getString("name") + "");
            }
//            Log.d("JsonData", stationName.size()+json + "======================");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}