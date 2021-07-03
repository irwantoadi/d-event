package com.irwantostudio.devent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VendorActivity extends AppCompatActivity {

    Button yes, no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        yes = findViewById(R.id.btn_vendor_yes);
        no = findViewById(R.id.btn_vendor_no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                no.setBackgroundColor(Color.parseColor("#a4a4a4"));
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                yes.setBackgroundColor(Color.parseColor("#a4a4a4"));
            }
        });
    }
}
