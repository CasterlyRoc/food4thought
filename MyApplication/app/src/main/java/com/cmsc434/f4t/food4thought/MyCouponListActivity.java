package com.cmsc434.f4t.food4thought;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.*;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MyCouponListActivity extends AppCompatActivity{
    public final static String image = "image";
		public final static String title = "title";
    public final static String description = "description";
    public final static String business = "business";
		public final static String cost = "cost";

    static String[][] couponsList = new String[][]{
            {"McDonalds", "Buy 1 Get 1 Free: Large French Fries", "Buy 1 Large French Fries and get a second order of equal or lesser value for free. Valid until 12/30/2015 11:59:59 PM.", "200"},
            {"Taco Bell", "Free Taco", "Free taco. No purchase necessary. Valid until 12/30/2015 11:59:59 PM.", "100"},
            {"Chipotle", "$1 Off Steak Bowl",  "Get $1 off for purchase of any Steak Bowl. Valid until 12/30/2015 11:59:59 PM.", "100"}
    };
    ArrayList<Coupon> dataForTheAdapter = new ArrayList<Coupon>();
    static ArrayAdapter<Coupon> dataAdapter;
    static int deletePos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupons);

        // We get the ListView component from the layout
        ListView lv = (ListView) findViewById(R.id.listView1);

        for (int counter = 0; counter < couponsList.length; counter++) {
            dataForTheAdapter.add(new Coupon(couponsList[counter][0], couponsList[counter][1], couponsList[counter][2], Integer.parseInt(couponsList[counter][3]), counter));
        }

        // This is a simple adapter that accepts as parameter
        // Context
        // Data list
        // The row layout that is used during the row creation
        // The keys used to retrieve the data
        // The View id used to show the data. The key number and the view id must match
        dataAdapter = new ArrayAdapter<Coupon>(this, R.layout.country_list, dataForTheAdapter);

        lv.setAdapter(dataAdapter);

        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MyCouponListActivity.this, MyCouponActivity.class);
                Bundle extras = new Bundle();
                extras.putString(business, String.valueOf(dataAdapter.getItem(position).getBusiness()));
                extras.putString(title, String.valueOf(dataAdapter.getItem(position).getTitle()));
                extras.putString(description, String.valueOf(dataAdapter.getItem(position).getDescription()));
                extras.putString(cost, String.valueOf(dataAdapter.getItem(position).getCost()));
                intent.putExtras(extras);
                deletePos = (int) dataAdapter.getItem(position).getPosition();
                //dataAdapter.notifyDataSetChanged();

                startActivity(intent);
                //dataAdapter.remove(dataAdapter.getItem(position));

            }
        });

        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
                dataAdapter.notifyDataSetChanged();
            }
        });
    }
}
