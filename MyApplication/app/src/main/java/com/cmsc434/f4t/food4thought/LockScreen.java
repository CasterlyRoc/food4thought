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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class LockScreen extends AppCompatActivity {

    ArrayList<Integer> allowedDays = new ArrayList<>();
    final int START_HOUR = 21; // 9
    final int START_MIN = 30; // 30
    final int END_HOUR = 23; // 10
    final int END_MIN = 45; // 45

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LockScreen", "onCreate()");

        allowedDays.add(Calendar.TUESDAY);
        allowedDays.add(Calendar.THURSDAY);
        allowedDays.add(Calendar.SATURDAY);

        setupLocationListener();
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

        if (checkTime() && checkLocation()) {
            setContentView(R.layout.activity_lockscreen);
        }
   }

    private boolean checkTime() {
        // get the supported ids for GMT-05:00 (Eastern Standard Time)
        String[] ids = TimeZone.getAvailableIDs(-5 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        if (ids.length == 0)
            Log.d("LockScreen.java", "no ids returned!");

        // create a Eastern Standard Time time zone
        SimpleTimeZone est = new SimpleTimeZone(-5 * 60 * 60 * 1000, ids[0]);

        Calendar calendar = new GregorianCalendar(est);
        Date time = new Date();
        calendar.setTime(time);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (allowedDays.contains(day)) {
            if ((hour == START_HOUR && minute >= START_MIN) || (hour == END_HOUR && minute <= END_MIN)
                    || (hour > START_HOUR && hour < END_HOUR)) {

                Log.d("LockScreen", "checkTime() returning true");
                return true;
            } else {
                Log.d("LockScreen", "Time is out of range - " + hour + ":" + minute);
            }
        } else {
            Log.d("LockScreen", "Day isn't valid");
        }

        Log.d("LockScreen", "checkTime() returning false");
        return false;
    }

    private boolean checkLocation() {

        boolean valid = false;
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            int lat = (int) location.getLatitude();
            int lon = (int) location.getLongitude();
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
    }

    public void confirmUnlock(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LockScreen.this);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
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
