package android.example.com.trackinmetro.activity;

import android.example.com.trackinmetro.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgSwapRoute;
    Animation animRotateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animRotateView = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate_view);
        initComponent();

    }

    private void initComponent() {
        /**
         * ImageSwap View
         */
        imgSwapRoute = findViewById(R.id.imgSwapRoute);
        imgSwapRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSwapRoute.startAnimation(animRotateView);
            }
        });
    }
}
