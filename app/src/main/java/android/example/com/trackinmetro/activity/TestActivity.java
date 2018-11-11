package android.example.com.trackinmetro.activity;

import android.example.com.trackinmetro.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

public class TestActivity extends AppCompatActivity {

    ToggleButton button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button1 = findViewById(R.id.toogle1);
        button2 = findViewById(R.id.toogle2);
        button3 = findViewById(R.id.toogle3);
        button4 = findViewById(R.id.toogle4);
        button5 = findViewById(R.id.toogle5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button1.isChecked()) {
                    Log.d("toogle1",1+"");
                }
                else {
                    Log.d("toogle1",0+"");

                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button2.isChecked()) {
                    Log.d("toogle2",3+"");
                }
                else {
                    Log.d("toogle2",2+"");

                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button3.isChecked()) {
                    Log.d("toogle3",5+"");
                }
                else {
                    Log.d("toogle3",4+"");

                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button4.isChecked()) {
                    Log.d("toogle4",7+"");
                }
                else {
                    Log.d("toogle4",6+"");

                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button5.isChecked()) {
                    Log.d("toogle5",9+"");
                }
                else {
                    Log.d("toogle5",8+"");

                }
            }
        });
    }
}
