package com.cmsc434.f4t.food4thought;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateUser extends AppCompatActivity {

    private Spinner spinner;
    private static final String[]paths = {"item 1", "item 2", "item 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        spinner = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]
                {"Delaware University","Duke University","Flagler College","Georgetown University",
                "University of Maryland, Baltimore Count","University of Maryland, College Park"
                ,""};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }
}
