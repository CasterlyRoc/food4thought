package com.cmsc434.f4t.food4thought;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

public class MyCoupons extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private List<String> liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupons);
    }

    public void goToBuyCoupons(View view){
        Intent intent = new Intent(this, BuyCoupons.class);
        startActivity(intent);
    }

}
