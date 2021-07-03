package com.irwantostudio.devent;//package com.irwantostudio.devent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.irwantostudio.devent.R;

public class SubMenuActivity extends AppCompatActivity {

    String page;
    TextView no_teks;
    ImageView img_kosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        Toolbar toolbar;
//        toolbar = findViewById(R.id.toolbar_icon);
//        toolbar.setTitle("D'EVENT");
//        setSupportActionBar(toolbar);

        no_teks = findViewById(R.id.no_teks);
        img_kosong = findViewById(R.id.img_kosong);
        Intent i = getIntent();
        page = i.getStringExtra("page");
        switch(page) {
            case "task" :
                setTitle("Task List");
                img_kosong.setBackgroundResource(R.drawable.task_0);
                no_teks.setText("No Tasks Found \nPlease add your Tasks");
                break;
            case "guest" :
                setTitle("Guest");
                img_kosong.setBackgroundResource(R.drawable.guest_0);
                no_teks.setText("No Guests Found \nPlease add your Guests");
                break;
            case "budget" :
                setTitle("Budget");
                img_kosong.setBackgroundResource(R.drawable.budget_0);
                no_teks.setText("No Budget Found \nPlease add your Budget");
                break;
            case "vendor" :
                setTitle("Vendor");
                img_kosong.setBackgroundResource(R.drawable.vendor_0);
                no_teks.setText("No Vendors Found \nPlease add your Vendors");
                break;
            case "dashboard" :
                setTitle("Dashboard");
                no_teks.setText("");
                break;
            default:
                setTitle("D'EVENT");
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                switch(page) {
                    case "task" :
                        i = new Intent(SubMenuActivity.this, TaskActivity.class);
                        startActivity(i);
                        break;
                    case "guest" :
                        i = new Intent(SubMenuActivity.this, GuestActivity.class);
                        startActivity(i);
                        break;
                    case "budget" :
                        i = new Intent(SubMenuActivity.this, BudgetActivity.class);
                        startActivity(i);
                        break;
                    case "vendor" :
                        i = new Intent(SubMenuActivity.this, VendorActivity.class);
                        startActivity(i);
                        break;
                    case "dashboard" :
//                        setTitle("Dashboard");
                        break;
                    default:
//                        setTitle("D'EVENT");
                }
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar_icon, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
////            case R.id.action_akun:
////                // Do whatever you want to do on click.
////                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
