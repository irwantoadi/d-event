package com.irwantostudio.devent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BudgetActivity extends AppCompatActivity {

    Button yes, no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        yes = findViewById(R.id.btn_budget_yes);
        no = findViewById(R.id.btn_budget_no);
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
