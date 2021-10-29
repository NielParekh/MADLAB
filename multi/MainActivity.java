package com.example.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView banner, coord, counter_text;

    private Button counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (TextView) findViewById(R.id.banner);
        counter_text = (TextView) findViewById(R.id.counter);
        coord = (TextView) findViewById(R.id.coord);
        counter = (Button) findViewById(R.id.button);

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(counter_text.getText().toString());
                current++;
                counter_text.setText(String.valueOf(current));
            }});



        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            banner.animate().translationXBy(20f);
                            Random rnd = new Random();
                            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                            banner.setTextColor(color);
                            coord.animate().translationXBy(20f);
                            int[] location = new int[2];
                            banner.getLocationOnScreen(location);
                            coord.setText("Location: (" + location[0] + ", " + location[1] + ")");
                        }
                    });



                }
            }
        }).start();

    }
}

