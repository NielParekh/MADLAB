package com.example.graphics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_zoom=findViewById(R.id.bt_zoom);
        Button bt_rotate=findViewById(R.id.bt_rotate);
        Button bt_down=findViewById(R.id.bt_down);
        Button bt_up=findViewById(R.id.bt_up);
        Button bt_forward=findViewById(R.id.bt_forward);
        Button bt_backward=findViewById(R.id.bt_backward);
        final ImageView iv_animate=findViewById(R.id.iv_animate);

        bt_zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in));
            }
        });

        bt_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_in));
            }
        });
        bt_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationYBy(-300f).setDuration(600);
            }
        });
        bt_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationYBy(300f).setDuration(600);
            }
        });
        bt_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationXBy(300f).setDuration(600);
            }
        });
        bt_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationXBy(-300f).setDuration(600);
            }
        });

    }
    public void nextPage(View view) {
        Intent intent = new Intent(this, ShapesActivity.class);
        startActivity(intent);

    }

}