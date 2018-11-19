package android.example.com.trackinmetro.activity;

import android.example.com.trackinmetro.R;
import android.graphics.Matrix;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MapActivity extends AppCompatActivity {
    ActionBar actionBar;
    Matrix matrix;
    Float scale = 1f;
    ScaleGestureDetector SGD;
    ImageView imgMetroMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        actionBar = getSupportActionBar();
        imgMetroMap = findViewById(R.id.img_MetroMap);

        intiComponent();
    }

    private void intiComponent() {
        actionBar.setTitle("Metro Map");
        actionBar.setDisplayHomeAsUpEnabled(true);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imgMetroMap);
        photoView.update();

    }


}