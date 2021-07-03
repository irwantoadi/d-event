package com.irwantostudio.devent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.irwantostudio.devent.helper.DbHelper;

import java.util.Calendar;

public class TaskActivity extends AppCompatActivity {

    Button yes, no;
    EditText task_name, note, status, date;
    DatePickerDialog picker;
    DbHelper SQLite = new DbHelper(this);
    String id_task, s_task, s_note, s_status, s_date;
    String id, tag_task, tag_note, tag_status, tag_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        yes = findViewById(R.id.btn_task_yes);
        no = findViewById(R.id.btn_task_no);
        task_name = findViewById(R.id.task_name);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);
        date = findViewById(R.id.date);
        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(TaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                no.setBackgroundColor(Color.parseColor("#a4a4a4"));
                status.setText("Complete");
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                yes.setBackgroundColor(Color.parseColor("#a4a4a4"));
                status.setText("Pending");
            }
        });
//        Toolbar toolbar;
//        toolbar = findViewById(R.id.toolbar_task);
//        toolbar.setTitle("D'EVENT");
//        setSupportActionBar(toolbar);

        blank();

        id = getIntent().getStringExtra("id");
        tag_task = getIntent().getStringExtra("tag_task");
        tag_note = getIntent().getStringExtra("tag_note");
        tag_status = getIntent().getStringExtra("tag_status");
        tag_date = getIntent().getStringExtra("tag_date");
        FloatingActionButton fab = findViewById(R.id.done_task);

//        if( getIntent().getStringExtra("id") == true) {
//            setTitle("Edit Task");
//        }

        if (id == null || id == "") {
            setTitle("Add Task");
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),
//                            "Saved", Toast.LENGTH_SHORT).show();
                    save();
//                try {
//                    if (id.equals("") || id == null) {
//                        save();
//                    } else {
//                        edit();
//                    }
//                } catch (Exception e){
//                    Log.e("Submit", e.toString());
//                }
                }
            });
        } else {
            setTitle("Edit Task");
            task_name.setText(tag_task);
            note.setText(tag_note);
            status.setText(tag_status);
            date.setText(tag_date);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),
//                            "Saved", Toast.LENGTH_SHORT).show();
                    edit();
                }
            });
        }
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(task_name.getText()).equals(null) || String.valueOf(note.getText()).equals("") ||
                String.valueOf(status.getText()).equals(null) || String.valueOf(date.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input all required data ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(task_name.getText().toString().trim(), note.getText().toString().trim(),
                    status.getText().toString().trim(), date.getText().toString().trim());
            blank();Toast.makeText(getApplicationContext(),
                    "Saved!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(task_name.getText()).equals(null) || String.valueOf(note.getText()).equals("") ||
                String.valueOf(status.getText()).equals(null) || String.valueOf(date.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input all required data ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(id), task_name.getText().toString().trim(), note.getText().toString().trim(),
                    status.getText().toString().trim(), date.getText().toString().trim());
            blank();Toast.makeText(getApplicationContext(),
                    "Saved!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // Make blank all Edit Text
    private void blank() {
        id = null;
        task_name.requestFocus();
        note.setText(null);
        status.setText("Pending");
        date.setText(null);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar_ok, menu);
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
