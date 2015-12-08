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
import android.widget.SimpleAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyCouponListActivity extends AppCompatActivity{
    public final static String title = "title";
    public final static String description = "description";
    public final static String business = "business";
		public final static String cost = "cost";
    public static int deletePos = 0;
    static SimpleAdapter adapter;

    // Array of strings storing country names
    String[] countries = new String[] {
            "McDonalds",
            "Taco Bell",
            "Chipotle"
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
            R.drawable.mcdonalds_logo,
            R.drawable.taco_bell_logo,
            R.drawable.chipotle_logo
    };

    // Array of strings to store currencies
    String[] currency = new String[]{
            "Buy 1 Get 1 Free: Large Fries",
            "Free Taco",
            "$1 Off Steak Bowl"
    };

    // Array of strings to store currencies
    String[] descriptions = new String[]{
            "Buy 1 Large French Fries and get another order of French Fries of equal or lesser value for free. Valid until 12/30/2015 11:59:59 PM.",
            "Free Taco. No Purchase Necessary. Valid until 12/30/2015 11:59:59 PM.",
            "$1 Off with purchase of any Steak Bowl. Valid until 12/30/2015 11:59:59 PM."
    };

    // Array of strings to store currencies
    String[] pointCost = new String[]{
            "200",
            "100",
            "100"
    };
    // Each row in the list stores country name, currency and flag
    List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        

        for(int i=0;i<3;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", countries[i]);
            hm.put("cur", currency[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            hm.put("description","Description : " + descriptions[i]);
            hm.put("cost", pointCost[i]);
            hm.put("pos", Integer.toString(i));
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt","cur" };

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur, };

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MyCouponListActivity.this, MyCouponActivity.class);
                Bundle extras = new Bundle();
                HashMap<String, String> h = (HashMap<String, String>) adapter.getItem(position);

                extras.putString(business, h.get("txt"));
                extras.putString(title, h.get("cur"));
                extras.putString(description, h.get("description"));
                extras.putString(cost, h.get("cost"));
                intent.putExtras(extras);
                deletePos = Integer.parseInt(h.get("pos"));
                //dataAdapter.notifyDataSetChanged();

                startActivity(intent);
                //dataAdapter.remove(dataAdapter.getItem(position));

            }
        });

       /* EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
                adapter.notifyDataSetChanged();
            }
        });*/
    }
}
