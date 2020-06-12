package com.example.project4210;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project4210.adapter.RecordAdapter;
import com.example.project4210.handler.RecordHandler;
import com.example.project4210.models.RecordModel;

import java.util.Arrays;
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
        //recordHandler.deleteAllRecords(); // Delete all records in database
        //recordHandler.addRecord(new RecordModel(3.0f, "R U F")); // Add a example record to database

        listView = findViewById(R.id.listView_Records);
        listView.setAdapter(new RecordAdapter(this, recordHandler.getAllRecords()));

        tv_stats_best = findViewById(R.id.tv_stats_best);
        tv_stats_ao5 = findViewById(R.id.tv_stats_ao5);
        tv_stats_ao12 = findViewById(R.id.tv_stats_ao12);
        tv_stats_average = findViewById(R.id.tv_stats_average);

        generateStatistics();
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
                generateStatistics();
            }
        });
        alert.setNegativeButton("No", null);
        alert.create().show();
    }

    public void generateStatistics() {
        records = recordHandler.getAllRecords();
        tv_stats_best.setText(getStatisticsString(getPersonalBest()));
        tv_stats_ao5.setText(getStatisticsString(getAo5()));
        tv_stats_ao12.setText(getStatisticsString(getAo12()));
        tv_stats_average.setText(getStatisticsString(getAverage()));
    }

    public float getPersonalBest() {
        return (records.size() <= 0) ? -1 : getRecordTimeSortedArray()[0];
    }

    public float getAo5() {
        return (records.size() < 5) ? -1 : getAverageInRange(5);
    }

    public float getAo12() {
        return (records.size() < 12) ? -1 : getAverageInRange(12);
    }

    public float getAverage() {
        return (records.size() <= 0) ? -1 : getAverageInRange(records.size());
    }

    private float[] getRecordTimeSortedArray() {
        float[] recordTime = new float[records.size()];
        for (int i = 0; i < recordTime.length; i++) {
            recordTime[i] = records.get(i).getTime();
        }
        Arrays.sort(recordTime);
        return recordTime;
    }

    private float getAverageInRange(int firstN) {
        if (records.size() < firstN) return 0;
        float[] recordTime = Arrays.copyOfRange(getRecordTimeSortedArray(), 0, firstN);
        float sum = 0.0f;
        for (float time : recordTime) {
            sum += time;
        }
        return sum / firstN;
    }

    private String getStatisticsString(float num) {
        return (num == -1) ? "-" : String.valueOf(num);
    }
}