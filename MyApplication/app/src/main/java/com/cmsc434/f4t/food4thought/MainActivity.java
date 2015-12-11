package com.cmsc434.f4t.food4thought;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> allowedDays = new ArrayList<>();
    final int START_HOUR = 1; // 9
    final int START_MIN = 30; // 30
    final int END_HOUR = 23; // 10
    final int END_MIN = 45; // 45


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate()");
        setContentView(R.layout.activity_main);

        // allowedDays.add(Calendar.TUESDAY);
        // allowedDays.add(Calendar.THURSDAY);

    }

    public void gotoCreateUser(View view){
        Intent intent = new Intent(this, CreateUser.class);
        startActivity(intent);
    }

    public void goToBuyCoupons(View view){
        Intent intent = new Intent(this, BuyCoupons.class);
        startActivity(intent);
    }

    public void goToMyCoupons(View view){
        Intent intent = new Intent(this, MyCoupons.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // setupGPS();
        /*
        if (setupGPS() == true && checkTime() == true) {
            Intent intent = new Intent(this, LockScreen.class);
            startActivity(intent);
        }*/
    }

    private boolean setupGPS() {
        boolean setup = false;

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Check if GPS is enabled and if not send user to the GPS settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

            // Request permissions
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            setup = true;
        }

        return setup;
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

                Log.d("MainActivity", "checkTime() returning true");
                return true;
            } else {
                Log.d("MainActivity", "Time is out of range - " + hour + ":" + minute);
            }
        } else {
            Log.d("MainActivity", "Day isn't valid");
        }

        Log.d("MainActivity", "checkTime() returning false");
        return false;
    }

}

