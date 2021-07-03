package com.irwantostudio.devent.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.irwantostudio.devent.R;
import com.irwantostudio.devent.model.Data;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    public int getCount() {
        return items.size();
    }
    public Object getItem(int location) {
        return items.get(location);
    }
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView id_task = (TextView) convertView.findViewById(R.id.id_task);
        TextView view_task = (TextView) convertView.findViewById(R.id.view_task);
        TextView view_note = (TextView) convertView.findViewById(R.id.view_note);
        TextView view_status = (TextView) convertView.findViewById(R.id.view_status);
        TextView view_date = (TextView) convertView.findViewById(R.id.view_date);

        Data data = items.get(position);

        id_task.setText(data.getIdTask());
        view_task.setText(data.getTask());
        view_note.setText(data.getNote());
        view_status.setText(data.getStatus());
        view_date.setText(data.getDate());

        return convertView;
    }
}
