package com.irwantostudio.devent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.irwantostudio.devent.helper.DbHelper;

public class DashboardActivity extends AppCompatActivity {
    DbHelper SQLite = new DbHelper(this);
    TextView task_pending, task_complete;
    Integer i_task_pending, i_task_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        task_pending = findViewById(R.id.count_task_pending);
        task_complete = findViewById(R.id.count_task_complete);
        i_task_pending = SQLite.getTaskPending();
        i_task_complete = SQLite.getTaskComplete();
        task_pending.setText(i_task_pending.toString() + " Pending");
        task_complete.setText(i_task_complete.toString() + " Complete");
    }
}
