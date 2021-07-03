package com.irwantostudio.devent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.irwantostudio.devent.adapter.Adapter;
import com.irwantostudio.devent.helper.DbHelper;
import com.irwantostudio.devent.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {
    ListView listView;
    RecyclerView recyclerView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);
    LinearLayout nodatafound;
    String countryList[] = {"China", "australia", "Portugle", "America"};

    public static final String TAG_IDTASK = "id_task";
    public static final String TAG_TASK = "task";
    public static final String TAG_NOTE = "note";
    public static final String TAG_STATUS = "status";
    public static final String TAG_DATE = "date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//        Toolbar toolbar;
//        toolbar = findViewById(R.id.toolbar_task);
//        toolbar.setTitle("D'EVENT");
//        setSupportActionBar(toolbar);

        SQLite = new DbHelper(getApplicationContext());
        adapter = new Adapter(TaskListActivity.this, itemList);
        listView = (ListView)findViewById(R.id.list_view);
//        recyclerView = findViewById(R.id.recyclerView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_row, R.id.view_task, countryList);
        listView.setAdapter(adapter);

        nodatafound = findViewById(R.id.noDataFoundTextView);

        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaskListActivity.this, TaskActivity.class);
                startActivity(i);
            }
        });

        // long press listview to show edit and delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String id_task = itemList.get(position).getIdTask();
                final String task = itemList.get(position).getTask();
                final String note = itemList.get(position).getNote();
                final String status = itemList.get(position).getStatus();
                final String date = itemList.get(position).getDate();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(TaskListActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(TaskListActivity.this, TaskActivity.class);
                                intent.putExtra("id", id_task);
                                intent.putExtra("tag_task", task);
                                intent.putExtra("tag_note", note);
                                intent.putExtra("tag_status", status);
                                intent.putExtra("tag_date", date);
                                startActivity(intent);
                                break;
                            case 1:
                                SQLite.delete(Integer.parseInt(id_task));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();

//        listView.setAdapter((ListAdapter) adapter);
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id_task = row.get(i).get(TAG_IDTASK);
            String task = row.get(i).get(TAG_TASK);
            String note = row.get(i).get(TAG_NOTE);
            String status = row.get(i).get(TAG_STATUS);
            String date = row.get(i).get(TAG_DATE);

            Data data = new Data();

            data.setIdTask(id_task);
            data.setTask(task);
            data.setNote(note);
            data.setStatus(status);
            data.setDate(date);

//            nodatafound.setText(id_task + " " + task + " " + note + " " + status + " " + date);

            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
//        adapter.notify();
        if(row.size() > 0){
            nodatafound.setVisibility(View.GONE);
        }else{
            nodatafound.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}
