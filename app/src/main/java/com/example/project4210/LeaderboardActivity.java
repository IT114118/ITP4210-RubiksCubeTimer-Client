package com.example.project4210;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.project4210.adapter.LeaderboardAdapter;
import com.example.project4210.handler.HttpURLHandler;
import com.example.project4210.models.LeaderboardModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LeaderboardActivity extends AppCompatActivity {
    ListView listView_Leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        List<LeaderboardModel> records = new ArrayList<>();

        try {
            HttpURLHandler handler = new HttpURLHandler("http://" + HttpURLHandler.USBWebServerAddr + ":8080/get_record_list.php");
            try {
                JSONArray jsonArray = new JSONArray(handler.getReturnJson());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String rank = String.valueOf(jsonObject.getInt("rank"));
                    String username = jsonObject.getString("username");
                    String record = jsonObject.getString("record");
                    records.add(new LeaderboardModel(rank, username, record));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listView_Leaderboard = findViewById(R.id.listView_Leaderboard);
        listView_Leaderboard.setAdapter(new LeaderboardAdapter(this, records));
    }
}