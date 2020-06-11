package com.example.project4210.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.project4210.R;
import com.example.project4210.handler.RecordHandler;
import com.example.project4210.models.RecordModel;

import java.util.List;

public class RecordAdapter extends BaseAdapter {
    private Context context;
    private List<RecordModel> records;

    public RecordAdapter(Context context, List<RecordModel> recordModelList) {
        this.context = context;
        this.records = recordModelList;
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int i) {
        return records.get(i);
    }

    @Override
    public long getItemId(int i) {
        return records.get(i).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.record_item, null);
        }

        TextView textView_Time = convertView.findViewById(R.id.textView_Time);
        TextView textView_Scramble = convertView.findViewById(R.id.textView_Scramble);
        final CheckBox checkBox_Star = convertView.findViewById(R.id.checkBox_Star);

        textView_Time.setText(String.valueOf(records.get(position).getTime()));
        textView_Scramble.setText(records.get(position).getScramble());
        checkBox_Star.setChecked(records.get(position).isStarred());

        checkBox_Star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordHandler recordHandler = new RecordHandler(context);
                recordHandler.updateRecordStar(records.get(position).getId(), checkBox_Star.isChecked());
            }
        });

        return convertView;
    }
}
