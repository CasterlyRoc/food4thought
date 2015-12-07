package com.cmsc434.f4t.food4thought;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void gotoMyCoupon(View view){
        Intent intent = new Intent(this, MyCouponList.class);
        startActivity(intent);
    }
}
