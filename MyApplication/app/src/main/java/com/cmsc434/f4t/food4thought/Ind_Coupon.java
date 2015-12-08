package com.cmsc434.f4t.food4thought;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class Ind_Coupon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String points = intent.getStringExtra(BuyCoupons.EXTRA_MESSAGE);
        setContentView(R.layout.activity_ind__coupon);
        TextView textView = (TextView) findViewById(R.id.points_ind);
        textView.setText(points);

    }

    public void goBack(View view){
        Intent intent = new Intent(this, BuyCouponEffect.class);
        startActivity(intent);
    }
}
