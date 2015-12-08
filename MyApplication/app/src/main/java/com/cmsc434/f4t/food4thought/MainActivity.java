package com.cmsc434.f4t.food4thought;

import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoCreateUser(View view){
        Intent intent = new Intent(this, CreateUser.class);
        startActivity(intent);
    }

    public void gotoBuyCoupons(View view){
        Intent intent = new Intent(this, BuyCoupons.class);
        startActivity(intent);
    }







}

