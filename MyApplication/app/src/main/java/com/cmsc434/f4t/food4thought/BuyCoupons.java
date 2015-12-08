package com.cmsc434.f4t.food4thought;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BuyCoupons extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.cmsc434.food4thought.MESSAGE";
    ArrayAdapter<Button> dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coupons);
    }

    public void goToIndCoupon(View view){
        Intent intent = new Intent(this, Ind_Coupon.class);
        TextView text = (TextView) findViewById(R.id.points);
        String message = text.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToMyCoupons(View view){
        Intent intent = new Intent(this, MyCoupons.class);
        startActivity(intent);
    }

    public void goToBuyCoupons(View view){
        Intent intent = new Intent(this, BuyCouponEffect.class);
        startActivity(intent);
    }


}
