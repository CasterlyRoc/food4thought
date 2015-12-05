package com.cmsc434.f4t.food4thought;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.widget.Toolbar;


public class CreateUser extends AppCompatActivity {

    String[] values = new String[]{
            "Bowie State University",
            "Coppin State University",
            "Frostburg State University",
            "Morgan State University",
            "Salisbury University",
            "Towson University",
            "United States Naval Academy",
            "University of Baltimore",
            "Duke University",
            "University of Maryland, College Park",
            "University of Maryland, Baltimore County",
            "Cecil College",
            "Chesapeake College",
            "Penn State University",
            "Christopher Newport University"
    };

    ArrayAdapter<String> dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        LayoutInflater inflater = (LayoutInflater) getActionBar()
                .getThemedContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View customActionBarView = inflater.inflate(R.layout.action_bar, null);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(
                ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME
                        | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setCustomView(customActionBarView,
                new ActionBar.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        displayView();
    }

    private void displayView(){
        List<String> universityList = new ArrayList<>();
        for(String s: values){
            universityList.add(s);
        }
        //create an ArrayAdaptar from the String Array
        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.country_list, universityList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(CreateUser.this, UnivLogin.class);
                startActivity(intent);
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
            }
        });
    }
}
