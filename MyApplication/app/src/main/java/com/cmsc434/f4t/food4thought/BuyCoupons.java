package com.cmsc434.f4t.food4thought;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BuyCoupons extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.cmsc434.food4thought.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coupons);
        displayViewMcdonalds();
    }

    public void updatePoints(View view){
        TextView text = (TextView) findViewById(R.id.points);
        text.setText("Test");
    }

    public void goToIndCoupon(View view){
        Intent intent = new Intent(this, Ind_Coupon.class);
        TextView text = (TextView) findViewById(R.id.points);
        String message = text.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    private void displayViewMcdonalds(){
        List<Button> couponList = new ArrayList<>();
        Button button1 = new Button(this);
    }
}
