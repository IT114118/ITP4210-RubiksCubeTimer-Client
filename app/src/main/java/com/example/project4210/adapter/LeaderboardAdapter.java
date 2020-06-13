package com.example.project4210.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project4210.R;
import com.example.project4210.models.LeaderboardModel;

import java.util.List;

public class LeaderboardAdapter extends BaseAdapter {
    private Context context;
    private List<LeaderboardModel> records;

    public LeaderboardAdapter(Context context, List<LeaderboardModel> leaderboardModelList) {
        this.context = context;
        this.records = leaderboardModelList;
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
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.leaderboard_item, null);
        }

        TextView textView_Rank = convertView.findViewById(R.id.textView_Rank);
        TextView textView_UserName = convertView.findViewById(R.id.textView_UserName);
        TextView textView_Record = convertView.findViewById(R.id.textView_Record);

        textView_Rank.setText(String.valueOf(records.get(position).getRank()));
        textView_UserName.setText(String.valueOf(records.get(position).getUsername()));
        textView_Record.setText(String.valueOf(records.get(position).getScore()));

        return convertView;
    }
}
