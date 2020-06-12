package com.example.project4210;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project4210.adapter.RecordAdapter;
import com.example.project4210.handler.RecordHandler;
import com.example.project4210.models.RecordModel;

import java.util.List;

public class RecordActivity extends AppCompatActivity {

    RecordHandler recordHandler;
    List<RecordModel> records;
    ListView listView;

    TextView tv_stats_best, tv_stats_ao5, tv_stats_ao12, tv_stats_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recordHandler = new RecordHandler(this);
        records = recordHandler.getAllRecords();

        listView = findViewById(R.id.listView_Records);
        listView.setAdapter(new RecordAdapter(this, recordHandler.getAllRecords()));

        tv_stats_best = findViewById(R.id.tv_stats_best);
        tv_stats_ao5 = findViewById(R.id.tv_stats_ao5);
        tv_stats_ao12 = findViewById(R.id.tv_stats_ao12);
        tv_stats_average = findViewById(R.id.tv_stats_average);

        tv_stats_best.setText(String.valueOf(getPersonalBest()));
        tv_stats_ao5.setText(String.valueOf(getAo5()));
        tv_stats_ao12.setText(String.valueOf(getAo12()));
        tv_stats_average.setText(String.valueOf(getAverage()));
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void textView_DeleteAllRecords_OnClick(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirm Delete");
        alert.setMessage("Are you sure to delete all records?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recordHandler.deleteAllRecords();
                listView.setAdapter(new RecordAdapter(getApplicationContext(), recordHandler.getAllRecords()));
            }
        });
        alert.setNegativeButton("No", null);
        alert.create().show();
    }

    public float getPersonalBest() {
        if (records.size() == 0) return 0;
        float personalBest = records.get(0).getTime();
        for (int i = 1; i < records.size(); i++) {
            if (records.get(i).getTime() < personalBest) {
                personalBest = records.get(i).getTime();
            }
        }
        return personalBest;
    }

    public float getAo5() {
        if (records.size() <= 5) return 0;
        List<RecordModel> tempRecord = records;
        float[] best5 = new float[5];
        for (float oneOf : best5) {
            oneOf = tempRecord.get(0).getTime();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < tempRecord.size(); j++) {
                if (tempRecord.get(j).getTime() < best5[i]) {
                    best5[i] = tempRecord.get(j).getTime();
                    tempRecord.remove(j);
                }
            }
        }
        float total = 0;
        for (float oneOf : best5) {
            total += oneOf;
        }
        return total / 5;
    }

    public float getAo12() {
        if (records.size() <= 12) {
            Toast.makeText(RecordActivity.this, "a", Toast.LENGTH_SHORT).show();
            return 0;
        }
        List<RecordModel> tempRecord = records;
        float[] best12 = new float[12];
        for (float oneOf : best12) {
            oneOf = tempRecord.get(0).getTime();
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 1; j < tempRecord.size(); j++) {
                if (tempRecord.get(j).getTime() < best12[i]) {
                    best12[i] = tempRecord.get(j).getTime();
                    tempRecord.remove(j);
                }
            }
        }
        float total = 0;
        for (float oneOf : best12) {
            total += oneOf;
        }
        return total / 12;
    }

    public float getAverage() {
        if (records.size() == 0) return 0;
        float total = 0;
        for (int i = 0; i < records.size(); i++) {
            total += records.get(i).getTime();
        }
        return total / records.size();
    }
}