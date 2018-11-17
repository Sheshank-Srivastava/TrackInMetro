package android.example.com.trackinmetro.activity;

import android.content.Intent;
import android.example.com.trackinmetro.R;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class SplashActivity extends AppCompatActivity {
    public static int splash_once = 0;
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
}