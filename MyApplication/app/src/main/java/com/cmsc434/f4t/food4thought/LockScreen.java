package com.cmsc434.f4t.food4thought;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;

public class LockScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LockScreen", "onCreate()");
        // setupLocationListener();
        updatePoints();
    }

    private void updatePoints(){
        int delay = 1000; // delay for 5 sec.
        int period = 1000; // repeat every sec.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView view = (TextView) findViewById(R.id.session_points);
                        int tmp = Integer.parseInt(view.getText().toString());
                        tmp = tmp + 1;
                        view.setText(Integer.toString(tmp));

                    }
                });
            }
        }, 0, 3000);
    }

    private void setupLocationListener() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Log.d("MainActivity", "onLocationChanged(): " + location.toString());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("MainActivity", "***onStatusChanged()***" + "\nprovider: " + provider + "\nstatus: " + status);
            }

            public void onProviderEnabled(String provider) {
                Log.d("MainActivity", "onProviderEnabled()");
            }

            public void onProviderDisabled(String provider) {
                Log.d("MainActivity", "onProviderDisabled()");
            }

        };

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListener);
        } catch (SecurityException e) {
            Log.d("LockScreen", "SecurityException thrown when requesting location\n" +
                    e.getLocalizedMessage() + "\n\n" + e.getMessage());
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LockScreen", "onStart()");

        if (checkLocation()) {
            setContentView(R.layout.activity_lockscreen);
        }
   }


    private boolean checkLocation() {

        return true;
        /*
        boolean valid = false;
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            int lat, lon;
            if (location == null) {
                lat = (int) location.getLatitude();
                lon = (int) location.getLongitude();
            } else {
                lat = 38;
                lon = -76;
            }
            if ((lat == 38 || lat == 39) && (lon == -76 || lon == -77)) {
                Toast.makeText(LockScreen.this, "Current Location: " + location.getLatitude() + ", "
                        + location.getLongitude() + "\nYou are in class!", Toast.LENGTH_SHORT).show();
                valid = true;
            } else {
                Log.d("LockScreen", "location return bad values: " + location);
                Toast.makeText(LockScreen.this, "location: " + location, Toast.LENGTH_LONG).show();
            }
        } catch (SecurityException e) {
            Log.d("LockScreen", "SecurityException caught");
            Toast.makeText(LockScreen.this, "Need location!", Toast.LENGTH_LONG).show();
        }

        Log.d("LockScreen", "checkLocation() returning " + valid);
        return valid;
        */
    }

    public void confirmUnlock(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LockScreen.this);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(LockScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setMessage(R.string.unlock_confirm);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startDialer(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }

}
