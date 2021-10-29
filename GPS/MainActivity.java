package com.example.gps;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView latitude, longitude;
    Button button;
    private Object LocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        button = findViewById(R.id.button);

        LocationManager lm = (android.location.LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener l = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitude.setText(Double.toString((double)location.getLatitude()));
                longitude.setText(Double.toString((double) location.getLongitude()));
            }
        };
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                lm.requestLocationUpdates("gps", 5000, 0, l);
            }
        });

    }
}