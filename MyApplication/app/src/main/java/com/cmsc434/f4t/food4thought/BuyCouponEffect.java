package com.cmsc434.f4t.food4thought;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BuyCouponEffect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coupon_effect);
    }

    public void goToMyCoupons(View view){
        Intent intent = new Intent(this, MyCouponsEffect.class);
        startActivity(intent);
    }

}
