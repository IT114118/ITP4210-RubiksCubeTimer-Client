package com.example.project4210;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project4210.adapter.RecordAdapter;
import com.example.project4210.handler.RecordHandler;
import com.example.project4210.models.RecordModel;

public class RecordActivity extends AppCompatActivity {

    RecordHandler recordHandler;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recordHandler = new RecordHandler(this);
        //recordHandler.addRecord(new RecordModel(30.0f, "R U F", true));
        //recordHandler.addRecord(new RecordModel(40.0f, "R F F", false));
        //recordHandler.addRecord(new RecordModel(50.0f, "R B F", false));

        listView = findViewById(R.id.listView_Records);
        listView.setAdapter(new RecordAdapter(this, recordHandler.getAllRecords()));
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
}